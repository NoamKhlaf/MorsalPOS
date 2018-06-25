package sd.code.morsalpos.adapter;

/**
 * Created by master on 11/12/2016.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.CardInfo;

//Adapter to handle ListView items (Contacts).
public class CardListAdapter extends BaseAdapter {
    ArrayList<CardInfo> cards ;

    //Used to create view.
    private LayoutInflater mInflater;
    Context context  ;
    AlertDialog.Builder alertDialog ;

    public CardListAdapter(Context context , ArrayList<CardInfo> cards){
        this.cards =cards ;
        this.context =context ;
        mInflater = LayoutInflater.from(context);

    }
    /** Get the sd.code.morsalpos.adapter items (Contacts) count */
    public int getCount() {
        return cards.size();
    }
    /** Get item (Contact) by position */
    public Object getItem(int position) {
        return cards.get(position);
    }
    /** Get item (Contact) id by position */
    public long getItemId(int position) {
        return 0;
    }
    /** Get item (Contact) view by position */
    public View getView(final int position, View row, ViewGroup parent) {
        //Create view from contact_list_item.xml layout.
        if(row == null)
            row = mInflater.inflate(R.layout.card_name_item_design, null);
        //Set Contact name.
        if(cards !=null) {

            TextView name = (TextView) row.findViewById(R.id.card_name);
           name.setText(cards.get(position).getName()+" - "+cards.get(position).getPan());

        }
        return row;
    }

}
