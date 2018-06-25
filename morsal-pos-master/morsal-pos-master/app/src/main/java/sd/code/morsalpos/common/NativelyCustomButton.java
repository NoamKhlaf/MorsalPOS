package sd.code.morsalpos.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class NativelyCustomButton extends Button {

    public NativelyCustomButton(Context context) {
        super(context);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

    public NativelyCustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

    public NativelyCustomButton(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

}