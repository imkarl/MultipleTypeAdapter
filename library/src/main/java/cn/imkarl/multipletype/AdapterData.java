package cn.imkarl.multipletype;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class AdapterData {

    private final DataList headers = new DataList();
    private final DataList contents = new DataList();
    private final DataList footers = new DataList();

    public int getItemCount() {
        return headers.size() + contents.size() + footers.size();
    }

    @NonNull
    public Object getItemData(int position) {
        if (headers.size() > 0 && position < headers.size()){
            return headers.get(position);

        } else if (contents.size() > 0 && (position - headers.size()) < contents.size()){
            return contents.get(position - headers.size());

        } else if (footers.size() > 0 && (position - headers.size() - contents.size()) < footers.size()){
            return footers.get(position - headers.size() - contents.size());

        } else {
            throw new IndexOutOfBoundsException("position: "+position+", itemCount: "+getItemCount());
        }
    }

    public DataList getHeaders() {
        return headers;
    }

    public DataList getContents() {
        return contents;
    }

    public DataList getFooters() {
        return footers;
    }

    @SuppressWarnings({"ConstantConditions"})
    public static final class DataList {
        private final List<Object> datas = new ArrayList<>();

        public void setAll(@NonNull Collection headers) {
            clear();
            addAll(headers);
        }
        @NonNull
        public Object set(int index, @NonNull Object item) {
            if (item == null) {
                return false;
            }
            return this.datas.set(index, item);
        }

        public boolean addAll(@NonNull Collection datas) {
            if (datas.isEmpty()) {
                return false;
            }
            boolean hasAdd = false;
            for (Object data : datas) {
                if (data != null) {
                    this.datas.add(data);
                    hasAdd = true;
                }
            }
            return hasAdd;
        }
        public boolean addAll(int index, @NonNull Collection datas) {
            if (datas.isEmpty()) {
                return false;
            }
            boolean hasAdd = false;
            for (Object data : datas) {
                if (data != null) {
                    this.datas.add(index++, data);
                    hasAdd = true;
                }
            }
            return hasAdd;
        }
        public boolean add(@NonNull Object item) {
            if (item == null) {
                return false;
            }
            this.datas.add(item);
            return true;
        }
        public boolean add(int index, @NonNull Object item) {
            if (item == null) {
                return false;
            }
            this.datas.add(index, item);
            return true;
        }

        @NonNull
        public Object remove(int index) {
            return this.datas.remove(index);
        }
        public boolean remove(@NonNull Object item) {
            return this.datas.remove(item);
        }
        public void clear() {
            this.datas.clear();
        }

        @NonNull
        public Object get(int index) {
            return this.datas.get(index);
        }
        public int indexOf(@NonNull Object item) {
            return this.datas.indexOf(item);
        }
        public int lastIndexOf(@NonNull Object item) {
            return this.datas.lastIndexOf(item);
        }
        public boolean contains(@NonNull Object item) {
            return this.datas.contains(item);
        }
        public boolean isEmpty() {
            return this.datas.isEmpty();
        }

        public int size() {
            return this.datas.size();
        }
    }

}
