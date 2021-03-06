package com.gianmoura.booker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gianmoura.booker.R;
import com.gianmoura.booker.helper.FragmentCustomModal;
import com.gianmoura.booker.helper.Utils;
import com.gianmoura.booker.model.Category;
import com.gianmoura.booker.model.Preference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.InnerViewHolder>
{
    private final List<Category> categories;
    private final List<Preference> preferences;
    private Context context;

    public CategoryAdapter(
            @NonNull final List<Category> list,
            @NonNull final List<Preference> preferences)
    {
        this.categories = list;
        this.preferences = preferences;
    }

    public static class InnerViewHolder
            extends
            RecyclerView.ViewHolder
    {
        @BindView(R.id.list_preferences_category)
        TextView preferenceCategoryView;
        public View view;

        public InnerViewHolder(
                @NonNull final View itemView )
        {
            super( itemView );
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(
            @NonNull final ViewGroup viewGroup,
            final int i )
    {
        context = viewGroup.getContext();
        final View view = LayoutInflater.from( context ).inflate( R.layout.list_preferences, viewGroup,
                false );
        final InnerViewHolder innerViewHolder = new InnerViewHolder( view );
        return innerViewHolder;
    }

    @Override
    public void onBindViewHolder(
            @NonNull final InnerViewHolder innerViewHolder,
            final int position )
    {
        if(categories.size() > 0){
            final Category category = categories.get(position);
            innerViewHolder.preferenceCategoryView.setText(category.getTag());

            innerViewHolder.view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(
                        final View v )
                {
                    final FragmentCustomModal removeModal = FragmentCustomModal.getInstance(context, R.layout.dialog_confirmaton);
                    TextView message = removeModal.getView().findViewById(R.id.dialog_confirmation_message);
                    ((TextView)removeModal.getView().findViewById(R.id.dialog_confirmation_title)).setText("Adicionar");
                    message.setText("Deseja adicionar esta categoria às suas preferências?");

                    (removeModal.getView().findViewById(R.id.dialog_confirmation_button_no)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeModal.hide();
                        }
                    });
                    (removeModal.getView().findViewById(R.id.dialog_confirmation_button_yes)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            categories.remove(position);
                            notifyDataSetChanged();
                            addCategory(category);
                            removeModal.hide();
                        }
                    });
                    removeModal.show();
                }

                private void addCategory(Category category) {
                    //adiciona category a lista de preferencias
                    Preference preference = new Preference();
                    preference.setTag(category.getTag());
                    preference.setActivity(1);
                    preference.setCid(category.getCid());
                    preference.setUid(Utils.getLoggedUid());
                    preferences.add(preference);
                }
            } );
        }
    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }
}
