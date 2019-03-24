package si.lg.vzorcniprojekt.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import si.lg.vzorcniprojekt.DataClass;
import si.lg.vzorcniprojekt.ListViewItemCheckboxBaseAdapter;
import si.lg.vzorcniprojekt.Objects.ListViewItemDTO;
import si.lg.vzorcniprojekt.Objects.SaveObj;
import si.lg.vzorcniprojekt.Objects.Tag;
import si.lg.vzorcniprojekt.R;
import si.lg.vzorcniprojekt.VolleyTool;

public class MainActivity extends AppCompatActivity {
    private List<ListViewItemDTO> ret;
    private ListView listViewWithCheckbox;
    private List<ListViewItemDTO> initItemList;
    private ListViewItemCheckboxBaseAdapter listViewDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_checkbox);

        SaveObj saveObj = new SaveObj();

        listViewWithCheckbox = (ListView) findViewById(R.id.list_view_with_checkbox);
        initItemList = this.getInitViewItemDtoList();
        listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);
        listViewDataAdapter.notifyDataSetChanged();

        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        onItemClickListSet();

        DataClass.scheduleJob(this);
    }

    private void onItemClickListSet() {
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO) itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if (itemDto.isChecked()) {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                } else {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                for (int i = 0; i < adapterView.getAdapter().getCount(); i++) {
                    SaveObj.selectedTags.clear();

                    ListViewItemDTO item = (ListViewItemDTO)adapterView.getAdapter().getItem(i);
                    if (item.isChecked()) {
                        SaveObj.selectedTags.add(item.getTag());
                    }
                }
            }
        });
    }
    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList() {
        String url = "http://192.168.1.153:8080/v1/api/tags";
        VolleyTool vt = new VolleyTool(this, url);
        ret = new ArrayList<>();

        vt.executeRequest(Request.Method.GET, new VolleyTool.VolleyCallback() {

            @Override
            public void getResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray JSONresponse = new JSONArray(response);
                        for (int i = 0; i < JSONresponse.length(); i++) {
                            JSONObject item = JSONresponse.getJSONObject(i);
                            String itemText = item.getString("name");
                            int itemInt = item.getInt("id");

                            Tag t = new Tag(itemText, itemInt);
                            SaveObj.tags.add(t);
                            ListViewItemDTO dto = new ListViewItemDTO();
                            dto.setChecked(false);
                            dto.setItemText(itemText);
                            dto.setTag(t);

                            ret.add(dto);
                        }

                        listViewDataAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return ret;
    }
}
