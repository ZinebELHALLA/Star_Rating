package com.example.starrate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.starrate.adapters.StarAdapter;
import com.example.starrate.beans.Star;
import com.example.starrate.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Star> starList;
    private StarAdapter adapter;
    private SearchView searchView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        setTheme(isDarkTheme ? R.style.AppTheme_Dark : R.style.AppTheme_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        starList = new ArrayList<>(StarService.getInstance().findAll());
        adapter = new StarAdapter(starList);
        recyclerView.setAdapter(adapter);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchView", "Texte entré: " + newText);
                filter(newText);
                return true;
            }
        });

        findViewById(R.id.menu_button).setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.change_theme) {
                toggleTheme();
                return true;
            } else if (itemId == R.id.share) {
                shareApp();
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private void toggleTheme() {
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        isDarkTheme = !isDarkTheme;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkTheme", isDarkTheme);
        editor.apply();

        // Apply the new theme
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Recreate the activity to apply the theme change
        recreate();
    }

    private void filter(String text) {
        List<Star> filteredList = new ArrayList<>();
        for (Star star : starList) {
            if (star.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(star);
            }
        }
        adapter.updateList(filteredList);
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Essayer cette nouvelle application");
        startActivity(Intent.createChooser(shareIntent, "Partager via"));
    }
}