package org.techern.minecraft.ModernHardcoreQuesting.tileentity;

import org.techern.minecraft.ModernHardcoreQuesting.util.Translator;

public enum PortalType {
    TECH("tech", true),
    MAGIC("magic", true),
    CUSTOM("custom", false);

    private String id;
    private boolean isPreset;

    PortalType(String id, boolean isPreset) {
        this.id = id;
        this.isPreset = isPreset;
    }

    public String getName() {
        return Translator.translate("modernhardcorequesting.portal." + this.id + ".title");
    }

    public String getDescription() {
        return Translator.translate("modernhardcorequesting.portal." + this.id + ".desc");
    }

    public boolean isPreset() {
        return isPreset;
    }
}
