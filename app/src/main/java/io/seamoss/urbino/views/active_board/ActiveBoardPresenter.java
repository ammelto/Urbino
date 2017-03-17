package io.seamoss.urbino.views.active_board;

import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.seamoss.urbino.base.mvp.BasePresenter;
import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.Node;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/5/2017.
 */

public class ActiveBoardPresenter extends BasePresenter<ActiveBoardView> {

    @JavascriptInterface
    public void showDimensions(int height, int width){
        Timber.d("Dimensions: " + height + " " + width);
    }

    @JavascriptInterface
    public void centerCameraOnNode(float px, float py){
        getView().moveCamera(px, py);
    }

    @JavascriptInterface
    public void selectNode(float px, float py, String name, Boolean complete, int challenge, String description, int bonus, int people){
        Node node = new Node();
        node.setName(name);
        node.setComplete(complete);
        node.setChallenge(challenge);
        node.setDescription(description);
        node.setMultiplier(bonus);
        node.setPeople(people);

        Timber.d("DESC " + description);

        getView().moveCamera(px, py);
        getView().selectNode(node);
    }

}
