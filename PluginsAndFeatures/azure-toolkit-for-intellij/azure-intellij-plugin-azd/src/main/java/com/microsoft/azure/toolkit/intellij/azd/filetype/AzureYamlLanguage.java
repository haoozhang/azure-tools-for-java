package com.microsoft.azure.toolkit.intellij.azd.filetype;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import org.jetbrains.yaml.YAMLLanguage;

public class AzureYamlLanguage extends Language {

    public static final AzureYamlLanguage INSTANCE = new AzureYamlLanguage();

    protected AzureYamlLanguage() {
        super(YAMLLanguage.INSTANCE, "AzureYAML");
    }
}
