package sd.code.morsalpos.adapter;

/**
 * Created by master on 11/12/2016.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import sd.code.morsalpos.ChangeiPinActivity;
import sd.code.morsalpos.R;
import sd.code.morsalpos.SuspendCardActivity;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.database.DatabaseHandler;

//Adapter to handle ListView items (Contacts).
public class CardAdapter extends BaseAdapter {
    ArrayList<CardInfo> cards ;

    //Used to create view.
    private LayoutInflater mInflater;
    Context context  ;
    AlertDialog.Builder alertDialog ;

    public CardAdapter(Context context , ArrayList<CardInfo> cards){
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
            row = mInflater.inflate(R.layout.card_item_design, null);
        //Set Contact name.
        if(cards !=null) {
            TextView name = (TextView) row.findViewById(R.id.name);
            TextView expDate = (TextView) row.findViewById(R.id.date);
            TextView pan = (TextView) row.findViewById(R.id.pan);
            TextView asDefault = (TextView) row.findViewById(R.id.asDefault);
            Button suspend = (Button) row.findViewById(R.id.btn_suspend);
            Button changeiPin = (Button) row.findViewById(R.id.btn_change_ipin);
            Button remove = (Button) row.findViewById(R.id.btn_remove);

            name.setText(cards.get(position).getName());
            expDate.setText("Exp Date: "+cards.get(position).getExpDate());
            pan.setText("PAN: "+cards.get(position).getPan());

            if(cards.get(position).getDefaultFlag() == 1){
                asDefault.setText("Default");
             }


            suspend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity
                    Intent intent = new Intent(context.getApplicationContext(),SuspendCardActivity.class);
                    context.startActivity(intent);

                }
            });

            changeiPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity
                    Intent intent = new Intent(context.getApplicationContext(),ChangeiPinActivity.class);
                    context.startActivity(intent);

                }
            });

             remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity


                    confirmDialog(position);


                }
            });



        }
        return row;
    }

    private  void confirmDialog(final int position) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);

        builder
                .setMessage("Are you sure?")
                .setPositiveButton("Delete",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                        DatabaseHandler db = new DatabaseHandler(context.getApplicationContext());
                        db.deleteCard(cards.get(position));
                        cards.clear();
                        cards.addAll(db.getAllCards());
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

}
