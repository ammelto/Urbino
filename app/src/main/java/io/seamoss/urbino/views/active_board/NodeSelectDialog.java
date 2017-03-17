package io.seamoss.urbino.views.active_board;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.data.models.Node;
import io.seamoss.urbino.views.home.HomeActivity;
import io.seamoss.urbino.views.home.HomeView;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class NodeSelectDialog extends DialogFragment {

    @BindView(R.id.dialog_node_title)
    TextView title;

    @BindView(R.id.nodes_bonus_text)
    TextView bonus;

    @BindView(R.id.node_dialog_description)
    TextView description;

    @BindView(R.id.node_people_completed)
    TextView completed;

    private Node node;

    public static NodeSelectDialog newInstance(Node node) {

        Bundle args = new Bundle();

        NodeSelectDialog fragment = new NodeSelectDialog();
        fragment.setArguments(args);
        fragment.setNode(node);
        return fragment;
    }

    public void setNode(Node node){
        this.node = node;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_node_info, null);
        ButterKnife.bind(this, view);

        title.setText(node.getName());
        description.setText(node.getDescription());
        String s = getResources().getStringArray(R.array.challenges)[node.getChallenge()];
        s = String.format(s, node.getMultiplier());
        bonus.setText(s);
        completed.setText(String.format(getString(R.string.node_dialog_completed), node.getPeople(), 30));

        builder.setView(view);
        builder.setPositiveButton(R.string.node_dialog_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                ActiveBoardView activeBoardView = (ActiveBoardActivity) getActivity();
                activeBoardView.openNode(node);

            }
        });
        builder.setNegativeButton(R.string.node_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                io.seamoss.urbino.views.active_board.NodeSelectDialog.this.getDialog().cancel();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
