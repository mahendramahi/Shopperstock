package com.mahendra.shopperstock.Commons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Locale;

/**
 * Text view with a custom font.
 * <p/>
 * In the XML, use something like {@code customAttrs:customFont="roboto-thin"}. The list of fonts
 * that are currently supported are defined in the enum {@link CustomFont}. Remember to also add
 * {@code xmlns:customAttrs="http://schemas.android.com/apk/res-auto"} in the header.
 */
public class CustomFontTextView extends TextView {

    private static final String sScheme =
            "http://schemas.android.com/apk/res-auto";
    private static final String sAttribute = "customFont";

    static enum CustomFont {
        ROBOTO_THIN("fonts/Roboto-Thin.ttf"),
        ROBOTO_LIGHT("fonts/Roboto-Light.ttf"),
        HelveticaLTStd_Blk("fonts/HelveticaLTStd-Blk.otf"),
        HelveticaLTStd_Roman("fonts/HelveticaLTStd-Roman.otf")
        ;

        private final String fileName;

        CustomFont(String fileName) {
            this.fileName = fileName;
        }

        static CustomFont fromString(String fontName) {
            return CustomFont.valueOf(fontName.toUpperCase(Locale.US));
        }

        public Typeface asTypeface(Context context) {
            return Typeface.createFromAsset(context.getAssets(), fileName);
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        } else {
            final String fontName = attrs.getAttributeValue(sScheme, sAttribute);

            if (fontName == null) {
                throw new IllegalArgumentException("You must provide \"" + sAttribute + "\" for your text view");
            } else {
                final Typeface customTypeface = CustomFont.fromString(fontName).asTypeface(context);
                setTypeface(customTypeface);
            }
        }
    }
}