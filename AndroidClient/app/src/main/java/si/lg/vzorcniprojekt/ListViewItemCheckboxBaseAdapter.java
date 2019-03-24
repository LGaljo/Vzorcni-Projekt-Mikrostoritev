package si.lg.vzorcniprojekt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.List;

import si.lg.vzorcniprojekt.Objects.ListViewItemDTO;

public class ListViewItemCheckboxBaseAdapter extends BaseAdapter {
    private List<ListViewItemDTO> listViewItemDtoList = null;
    private Context ctx = null;

    public ListViewItemCheckboxBaseAdapter(Context ctx, List<ListViewItemDTO> listViewItemDtoList) {
        this.ctx = ctx;
        this.listViewItemDtoList = listViewItemDtoList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (listViewItemDtoList != null) {
            ret = listViewItemDtoList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if (listViewItemDtoList != null) {
            ret = listViewItemDtoList.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ListViewItemViewHolder viewHolder = null;

        if (convertView != null) {
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(ctx, R.layout.activity_list_view_with_checkbox_item, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);

            viewHolder = new ListViewItemViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);


            convertView.setTag(viewHolder);
        }

        ListViewItemDTO listViewItemDto = listViewItemDtoList.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemCheckbox().setText(listViewItemDto.getItemText());

        return convertView;
    }
}