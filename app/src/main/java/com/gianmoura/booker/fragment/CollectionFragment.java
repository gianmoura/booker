package com.gianmoura.booker.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gianmoura.booker.R;
import com.gianmoura.booker.adapter.CollectionAdapter;
import com.gianmoura.booker.model.Book;
import com.gianmoura.booker.model.BookOwner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CollectionFragment extends Fragment {

    @BindView(R.id.bookCollectionList)
    RecyclerView cListView;
    private List<Book> collection;
    private RecyclerView.Adapter adapter;

    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this, view);

        collection = new ArrayList<Book>();
        adapter = new CollectionAdapter(collection);
        cListView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        cListView.setAdapter(adapter);
        getCollection();
        return view;
    }

    private void getCollection(){
        Book book = new Book();
        BookOwner bookOwner = new BookOwner();
        bookOwner.setQuantity(1);
        bookOwner.setDescription("Livro seminovo, em ótimo estado. Mando fotos pelo chat.");
        bookOwner.setValue(39.99);
        book.setTitle("O guia definitivo do mochileiro das galáxias");
        book.setSmallThumbnail("http://books.google.com/books/content?id=1yYuB7N0HPIC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api");
        book.setThumbnail("http://books.google.com/books/content?id=1yYuB7N0HPIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api");
        book.setAuthors(Arrays.asList("Douglas Adams"));
        book.setCategories(Arrays.asList("Fiction"));
        book.setOwner(bookOwner);
        collection.clear();
        collection.add(book);
        collection.add(book);
        collection.add(book);
        collection.add(book);
        adapter.notifyDataSetChanged();
    }

}
