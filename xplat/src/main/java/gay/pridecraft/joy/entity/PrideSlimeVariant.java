package gay.pridecraft.joy.entity;

import com.mojang.serialization.Codec;
import gay.pridecraft.joy.JoyUtil;
import gay.pridecraft.joy.registry.JoyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum PrideSlimeVariant implements StringIdentifiable {
    GAY("gay"),
    LESBIAN("lesbian"),
    BISEXUAL("bisexual"),
    TRANS("trans"),
    AGENDER("agender"),
    APLATONIC("aplatonic"),
    AROACE("aroace"),
    ARO("aro"),
    ACE("ace"),
    BIGENDER("bigender"),
    GENDERFLUID("genderfluid"),
    INTERSEX("intersex"),
    MLM("mlm"),
    ENBY("enby"),
    PAN("pan"),
    QUEER("queer");

    private final String name;
    public static final Codec<PrideSlimeVariant> CODEC = StringIdentifiable.createCodec(PrideSlimeVariant::values);

    PrideSlimeVariant(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public Identifier getTexture() {
        return JoyUtil.id("textures/entity/slime/slime_" + this.name + ".png");
    }

    public Item getDrop() {
        return JoyItems.SLIME_BALLS.get(this);
    }


}
