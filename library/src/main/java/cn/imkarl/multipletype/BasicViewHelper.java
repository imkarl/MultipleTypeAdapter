package cn.imkarl.multipletype;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 包含基本操作的ViewHelper
 * @author imkarl
 */
public class BasicViewHelper {

    @NonNull
    private final View itemView;
    @NonNull
    private final SparseArray<View> views;

    public int position = -1;

    public BasicViewHelper(@NonNull View itemView) {
        this.itemView = itemView;
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BasicViewHelper setTag(@IdRes int viewId, @Nullable Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }
    @Nullable
    public <T> T getTag(@IdRes int viewId) {
        return (T) getView(viewId).getTag();
    }
    public BasicViewHelper setTag(@IdRes int viewId, @IdRes int key, @Nullable Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }
    public <T> T getTag(@IdRes int viewId, @IdRes int key) {
        return (T) getView(viewId).getTag(key);
    }

    public BasicViewHelper setVisible(@IdRes int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BasicViewHelper setText(@IdRes int viewId, @Nullable CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }
    public BasicViewHelper setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setText(resId);
        return this;
    }
    public BasicViewHelper setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public BasicViewHelper setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }
    public BasicViewHelper setImageURI(@IdRes int viewId, @Nullable Uri imageUri) {
        ImageView view = getView(viewId);
        view.setImageURI(imageUri);
        return this;
    }

    public BasicViewHelper setBackgroundResource(@IdRes int viewId, @DrawableRes int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    public BasicViewHelper setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BasicViewHelper setSelected(@IdRes int viewId, boolean selected) {
        View view = getView(viewId);
        view.setSelected(selected);
        return this;
    }

    public BasicViewHelper setOnClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    @NonNull
    public Context getContext() {
        return itemView.getContext();
    }

}
