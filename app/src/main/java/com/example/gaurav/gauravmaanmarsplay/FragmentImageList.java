package com.example.gaurav.gauravmaanmarsplay;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentImageList extends Fragment {

    private int mColumnCount = 2;
    ViewModelMain viewModelMain;
    private FirestoreRecyclerAdapter<ImageMetaData, ImageRecyclerViewHolder> adapter;

    public FragmentImageList() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelMain = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ViewModelMain.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_image_list, container, false);

        FirestoreRecyclerOptions<ImageMetaData> options = new FirestoreRecyclerOptions.Builder<ImageMetaData>()
                .setQuery(viewModelMain.getImagesQuery(), ImageMetaData.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ImageMetaData, ImageRecyclerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ImageRecyclerViewHolder holder, int position, @NonNull ImageMetaData model) {
                holder.setProductName(model);
            }


            @NonNull
            @Override
            public ImageRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_image_list_item, parent, false);
                return new ImageRecyclerViewHolder(view);
            }
        };

        final int orientation = getActivity().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mColumnCount = 3;
        } else {
            mColumnCount = 2;
        }


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        return view;

    }


    private class ImageRecyclerViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imageView;

        ImageRecyclerViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.image_View);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity()
                            , ActivityShowImage.class)
                            .putExtra(Intent.EXTRA_TEXT, adapter.getItem(getAdapterPosition()).getUrl()));
                }
            });
        }

        void setProductName(ImageMetaData data) {
            Picasso.get().load(data.getUrl()).fit().into(imageView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
