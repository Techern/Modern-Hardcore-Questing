package org.techern.minecraft.ModernHardcoreQuesting.client.interfaces.edit;

import org.techern.minecraft.ModernHardcoreQuesting.client.interfaces.GuiBase;
import org.techern.minecraft.ModernHardcoreQuesting.client.interfaces.GuiQuestBook;
import org.techern.minecraft.ModernHardcoreQuesting.quests.Quest;
import org.techern.minecraft.ModernHardcoreQuesting.quests.RepeatInfo;
import org.techern.minecraft.ModernHardcoreQuesting.quests.RepeatType;
import org.techern.minecraft.ModernHardcoreQuesting.util.SaveHelper;
import org.techern.minecraft.ModernHardcoreQuesting.util.Translator;
import net.minecraft.entity.player.EntityPlayer;

public class GuiEditMenuRepeat extends GuiEditMenuExtended {

    private Quest quest;
    private RepeatType type;
    private int days;
    private int hours;

    public GuiEditMenuRepeat(GuiQuestBook gui, EntityPlayer player, Quest quest) {
        super(gui, player, true, 25, 20, 25, 100);
        this.quest = quest;
        this.type = quest.getRepeatInfo().getType();
        days = quest.getRepeatInfo().getDays();
        hours = quest.getRepeatInfo().getHours();

        textBoxes.add(new TextBoxHidden(gui, 0, "modernhardcorequesting.repeatMenu.days") {
            @Override
            protected int getValue() {
                return days;
            }

            @Override
            protected void setValue(int number) {
                days = number;
            }
        });

        textBoxes.add(new TextBoxHidden(gui, 1, "modernhardcorequesting.repeatMenu.hours") {
            @Override
            protected void draw(GuiBase gui, boolean selected) {
                super.draw(gui, selected);

                gui.drawString(gui.getLinesFromText(Translator.translate("modernhardcorequesting.repeatMenu.mcDaysHours"), 0.7F, 150), BOX_X, BOX_Y + BOX_OFFSET * 2 + TEXT_OFFSET, 0.7F, 0x404040);
            }

            @Override
            protected int getValue() {
                return hours;
            }

            @Override
            protected void setValue(int number) {
                hours = number;
            }
        });
    }

    @Override
    public void save(GuiBase gui) {
        quest.setRepeatInfo(new RepeatInfo(type, days, hours));
        SaveHelper.add(SaveHelper.EditType.REPEATABILITY_CHANGED);
    }

    @Override
    protected void onArrowClick(boolean left) {
        if (left) {
            type = RepeatType.values()[(type.ordinal() + RepeatType.values().length - 1) % RepeatType.values().length];
        } else {
            type = RepeatType.values()[(type.ordinal() + 1) % RepeatType.values().length];
        }
    }

    @Override
    protected String getArrowText() {
        return type.getName();
    }

    @Override
    protected String getArrowDescription() {
        return type.getDescription();
    }

    private abstract class TextBoxHidden extends TextBoxNumber {

        public TextBoxHidden(GuiQuestBook gui, int id, String title) {
            super(gui, id, title);
        }

        @Override
        protected boolean isVisible() {
            return type.isUseTime();
        }
    }
}
