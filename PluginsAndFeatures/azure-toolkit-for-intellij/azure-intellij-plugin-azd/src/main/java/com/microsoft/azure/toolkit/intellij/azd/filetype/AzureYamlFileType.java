package com.microsoft.azure.toolkit.intellij.azd.filetype;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.microsoft.azure.toolkit.intellij.azd.utils.AzdIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AzureYamlFileType extends LanguageFileType {

    public static final AzureYamlFileType INSTANCE = new AzureYamlFileType();

    private AzureYamlFileType() {
        super(AzureYamlLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Azure YAML";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Azure YAML configuration file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "yaml";
    }

    @Override
    public Icon getIcon() {
        return AzdIcons.LOGO;
    }
}
