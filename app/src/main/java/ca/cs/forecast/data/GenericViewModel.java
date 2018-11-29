package ca.cs.forecast.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericViewModel<T> extends ViewModel {
    protected MutableLiveData<List<T>> itemList;
    private int selectedIndex;

    private List<RecyclerView.Adapter> adapterList;

    public GenericViewModel() {
        adapterList = new ArrayList<>();
    }

    public List<T> getItemList() {
        if (itemList == null) {
            itemList = new MutableLiveData<List<T>>();
            selectedIndex = 0;
            loadData();
        }
        return itemList.getValue();
    }

    public T getSelectedItem() {
        return getItemList().get(selectedIndex);
    }

    public void setSelectedItem(T item) {
        selectedIndex = getItemList().indexOf(item);
    }

    public void removeItem(T item) {
        for (RecyclerView.Adapter adapter : adapterList) {
            adapter.notifyItemRemoved(itemList.getValue().indexOf(item));
        }
        itemList.getValue().remove(item);
    }

    public void addAdapter(RecyclerView.Adapter adapter) {
        adapterList.add(adapter);
    }

    public void removeAdapter(RecyclerView.Adapter adapter) {
        adapterList.remove(adapter);
    }

    public void clearAdapters() {
        adapterList.clear();
    }

    protected abstract void loadData();
}
