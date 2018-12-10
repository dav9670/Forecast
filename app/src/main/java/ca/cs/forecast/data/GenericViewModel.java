package ca.cs.forecast.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericViewModel<T> extends ViewModel {
    protected MutableLiveData<List<T>> itemList;
    private T selectedItem;

    private List<RecyclerView.Adapter> adapterList;

    public GenericViewModel() {
        adapterList = new ArrayList<>();
    }

    /**
     * If data has not already been loaded, loads the data, then return the data
     *
     * @return A list of object of type T
     */
    public List<T> getItemList() {
        if (itemList == null) {
            itemList = new MutableLiveData<List<T>>();
            selectedItem = null;
            loadData();
        }
        return itemList.getValue();
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T item) {
        selectedItem = item;
    }

    /**
     * Remove item from data list, and notify all adapters of the index of the item that has been removed
     * @param item
     */
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
