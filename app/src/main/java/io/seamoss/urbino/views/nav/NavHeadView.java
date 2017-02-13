package io.seamoss.urbino.views.nav;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;

/**
 * Created by Alexander Melton on 2/12/2017.
 */

public class NavHeadView extends LinearLayout{

    @BindView(R.id.nav_profile_image)
    ImageView profilePic;
    @BindView(R.id.nav_profile_name)
    TextView profileName;
    @BindView(R.id.nav_active_board)
    TextView activeBoard;


    public NavHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.nav_head, this);
        ButterKnife.bind(this);

    }

}
