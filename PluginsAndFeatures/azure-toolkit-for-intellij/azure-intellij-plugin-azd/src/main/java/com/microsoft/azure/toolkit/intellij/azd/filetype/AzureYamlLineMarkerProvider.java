package com.microsoft.azure.toolkit.intellij.azd.filetype;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.FunctionUtil;
import com.microsoft.azure.toolkit.intellij.azd.utils.AzdIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLMapping;

public class AzureYamlLineMarkerProvider implements LineMarkerProvider {

    @Override
    public @Nullable LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (!(element instanceof YAMLKeyValue keyValue)) {
            return null;
        }

        final String keyName = keyValue.getKeyText();
        final Project project = keyValue.getProject();

        // If the "services" key is found at top level, add a marker to run "azd up"
        if ("services".equals(keyName) && keyValue.getParent() != null) {
            return createLineMarker(keyValue, "azd up", () -> runAzdCommand(project, "azd up"));
        }

        // If an individual service is found inside the "services" key, add a marker for "azd deploy <service>"
        if (keyValue.getParent() instanceof YAMLMapping && keyValue.getParent().getParent() instanceof YAMLKeyValue parentKeyValue) {
            if ("service".equals(parentKeyValue.getKeyText())) {
                return createLineMarker(keyValue, "azd deploy " + keyName, () -> runAzdCommand(project, "azd deploy " + keyName));
            }
        }

        return null;
    }

    private LineMarkerInfo<PsiElement> createLineMarker(PsiElement keyValue, String tip, Runnable action) {
        return new LineMarkerInfo<>(
                keyValue,
                keyValue.getTextRange(),
                AzdIcons.UP,
                FunctionUtil.constant(tip),
                (mouseEvent, elt) -> action.run(), // handler
                GutterIconRenderer.Alignment.LEFT
        );
    }

    private void runAzdCommand(Project project, String command) {
        String directory = project.getBasePath();
        // todo: reuse logic of call azd command
    }
}
