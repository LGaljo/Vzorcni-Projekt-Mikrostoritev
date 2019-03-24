package si.lg.vzorcniprojekt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

public class ListViewItemViewHolder extends RecyclerView.ViewHolder {

    private CheckBox itemCheckbox;

    public ListViewItemViewHolder(View itemView) {
        super(itemView);
    }

    public CheckBox getItemCheckbox() {
        return itemCheckbox;
    }

    public void setItemCheckbox(CheckBox itemCheckbox) {
        this.itemCheckbox = itemCheckbox;
    }
}