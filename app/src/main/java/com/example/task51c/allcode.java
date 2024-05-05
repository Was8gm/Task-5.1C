//package com.example.task51c;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder2> {
//    private ArrayList<NewsItems> newsList;
//    private Context context;
//    private int position;
//
//    public ListAdapter(Context context, ArrayList<NewsItems> newsList) {
//        this.newsList = newsList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public  ListAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.news_card_item, parent, false);
//        return new  ListAdapter.MyViewHolder2(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
//        holder.mainNewsImage.setImageResource(newsList.get(position).getImage());
//        holder.mainNewsImage.setScaleType(ImageView.ScaleType.FIT_XY);
//        holder.mainNewsTitles.setText(newsList.get(position).getTitle());
//        //set listener for every cardView
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //create a new fragment
//                NewsDetailFragment fragment = new NewsDetailFragment();
//                //translate needed data
//                Bundle myBundle = new Bundle();
//                myBundle.putString("news_title",newsList.get(position).getTitle());
//                myBundle.putString("news_desc",newsList.get(position).getDesc());
//                myBundle.putInt("news_image",newsList.get(position).getImage());
//                fragment.setArguments(myBundle);
//                //replace news list fragment with detailed news fragment
//                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.testFragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return newsList.size();
//    }
//
//    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
//        ImageView mainNewsImage;
//        TextView mainNewsTitles;
//
//        public MyViewHolder2(@NonNull View itemView) {
//            super(itemView);
//            mainNewsImage = itemView.findViewById(R.id.news_item_image);
//            mainNewsTitles = itemView.findViewById(R.id.news_item_title);
//        }
//    }
//}
//package com.example.task51c;
//
//        import static java.security.AccessController.getContext;
//
//        import android.os.Bundle;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//
//        import androidx.fragment.app.Fragment;
//        import androidx.recyclerview.widget.GridLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.util.ArrayList;
//
//public class ListFragment extends Fragment {
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//    private RecyclerView mainNewsRecycleView;
//    private RecyclerView.LayoutManager layoutManager4Main;
//    private ListAdapter mainNewsAdapter;
//    private ArrayList<NewsItems> newsList;
//
//    public ListFragment() {
//        // Required empty public constructor
//    }
//    public ListFragment(ArrayList<NewsItems> newsList) {
//        this.newsList = newsList;
//    }
//
//
//    public static ListFragment newInstance(String param1, String param2) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.news_list_fragment, container, false);
//        mainNewsRecycleView = rootView.findViewById(R.id.mainNewsRecycleView);
//        mainNewsAdapter = new ListAdapter(getContext(),newsList);
//        layoutManager4Main = new GridLayoutManager(getContext(), 2);
//        mainNewsRecycleView.setLayoutManager(layoutManager4Main);
//        mainNewsRecycleView.setAdapter(mainNewsAdapter);
//        return rootView;
//    }
//}
//package com.example.task51c;
//
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.fragment.app.FragmentManager;
//        import androidx.fragment.app.FragmentTransaction;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import android.os.Bundle;
//        import android.os.Handler;
//
//        import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    ArrayList<NewsItems> newsList;
//    RecyclerView topNewsRecycleView;
//    RecyclerView.LayoutManager layoutManager4Top;
//    TopNews topNews;
//    private Handler handler;
//    private Runnable runnable;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initialNews();
//        topNewsRecycleView = findViewById(R.id.topNewsView);
//        //set top news images is horizontally scrolling
//        layoutManager4Top = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        topNews= new TopNews(this, newsList);
//        topNewsRecycleView.setLayoutManager(layoutManager4Top);
//        topNewsRecycleView.setAdapter(topNews);
//        //news list fragment will come firstly
//        ListFragment listFragment = new ListFragment(newsList);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.testFragment, listFragment);
//        fragmentTransaction.commit();
//        startAutoScroll();
//
//    }
//    private void startAutoScroll(){
//        int count = topNews.getItemCount();
//        handler = new Handler();
//        if(count>0){
//            if(runnable!=null){
//                handler.removeCallbacks(runnable);
//            }
//            runnable = new Runnable() {
//                int currentPosition = topNewsRecycleView.getVerticalScrollbarPosition();
//                @Override
//                public void run() {
//                    if (currentPosition == count - 1) {
//                        //if it's end of recycle, it will scroll to first image
//                        currentPosition = 0;
//                    } else {
//                        currentPosition++;
//                    }
//                    topNewsRecycleView.smoothScrollToPosition(currentPosition);
//                    //play the next image every 5s
//                    handler.postDelayed(this, 5000);
//                }
//            };
//            handler.postDelayed(runnable,5000);
//        }
//
//    }
//    private void initialNews() {
//        newsList = new ArrayList<>();
//        newsList.add(new NewsItems(
//                getString(R.string.news1_title),
//                getString(R.string.news1_desc),
//                R.drawable.news1
//        ));
//        newsList.add(new NewsItems(
//                getString(R.string.news2_title),
//                getString(R.string.news2_desc),
//                R.drawable.news2
//        ));
//        newsList.add(new NewsItems(
//                getString(R.string.news3_title),
//                getString(R.string.news3_desc),
//                R.drawable.news3
//        ));
//        newsList.add(new NewsItems(
//                getString(R.string.news4_title),
//                getString(R.string.news4_desc),
//                R.drawable.news4
//        ));
//        newsList.add(new NewsItems(
//                getString(R.string.news5_title),
//                getString(R.string.news5_desc),
//                R.drawable.news5
//        ));
//        newsList.add(new NewsItems(
//                getString(R.string.news6_title),
//                getString(R.string.news6_desc),
//                R.drawable.news6
//        ));
//    }
//}package com.example.task51c;
//
//        import android.os.Bundle;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ImageView;
//        import android.widget.TextView;
//
//        import androidx.fragment.app.Fragment;
//        import androidx.recyclerview.widget.GridLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.util.ArrayList;
//
//public class NewsDetailFragment  extends Fragment {
//
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//
//    private String mParam1;
//    private String mParam2;
//    private String news_title;
//    private String news_desc;
//    private TextView news_title_view, news_desc_view;
//    private ImageView news_image_view;
//    private int news_image;
//
//    public NewsDetailFragment() {
//        // Required empty public constructor
//    }
//
//
//    public static NewsDetailFragment newInstance(String param1, String param2) {
//        NewsDetailFragment fragment = new NewsDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.news_detail_fragment, container, false);
//
//        Bundle bundle = getArguments();
//        if(bundle!=null){
//            news_title = bundle.getString("news_title");
//            news_desc = bundle.getString("news_desc");
//            news_image = bundle.getInt("news_image");
//        }
//
//        //set title, description, and an image for detailed news fragment
//        news_title_view = rootView.findViewById(R.id.news_detail_title);
//        news_desc_view = rootView.findViewById(R.id.news_detail_desc);
//        news_image_view = rootView.findViewById(R.id.news_detail_image);
//        news_title_view.setText(news_title);
//        news_desc_view.setText(news_desc);
//        news_image_view.setImageResource(news_image);
//        news_image_view.setScaleType(ImageView.ScaleType.FIT_XY);
//        return rootView;
//    }
//}
//package com.example.task51c;
//
//public class NewsItems {
//    private String title;
//    private String desc;
//    private Integer image;
//
//
//    public NewsItems(String title, String desc, Integer image) {
//        this.title = title;
//        this.desc = desc;
//        this.image = image;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public Integer getImage() {
//        return image;
//    }
//
//    public void setImage(Integer image) {
//        this.image = image;
//    }
//}
//package com.example.task51c;
//
//        import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ImageView;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.security.PublicKey;
//        import java.util.ArrayList;
//
//public class TopNews extends RecyclerView.Adapter<TopNews.MyViewHolder> {
//    private Context mContext;
//    private  ArrayList<NewsItems> newsList;
//
//
//    public TopNews(Context mContext, ArrayList<NewsItems> newsList) {
//        this.mContext = mContext;
//        this.newsList = newsList;
//    }
//
//    @NonNull
//    @Override
//    public TopNews.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.top_news_pic, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TopNews.MyViewHolder holder, int position) {
//        holder.topNewsImage.setImageResource(newsList.get(position).getImage());
//        //set the image to suit imageView
//        holder.topNewsImage.setScaleType(ImageView.ScaleType.FIT_XY);
//
//
//    }
//
//
//
//    public int getItemCount() {
//        return newsList.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView topNewsImage;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            topNewsImage = itemView.findViewById(R.id.newImage);
//        }
//
//    }
//}
///////////////////////////////////////////////////////////////////////////////////////////////
///subtask

//package com.example.task51c_subtask;
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Bundle;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//public class Database extends SQLiteOpenHelper{
//    private static final String DATABASE_NAME = "Database";
//    private static final int DATABASE_VERSION = 1;
//
//
//    private static final String TABLE_USERS = "users";
//    private static final String COLUMN_USER_ID = "user_id";
//    private static final String COLUMN_FULL_NAME = "full_name";
//    private static final String COLUMN_USERNAME = "username";
//    private static final String COLUMN_PASSWORD = "password";
//
//
//    private static final String TABLE_PLAYLISTS = "playlists";
//    private static final String COLUMN_PLAYLIST_ID = "playlist_id";
//    private static final String COLUMN_PLAYLIST_NAME = "playlist_name";
//    private static final String COLUMN_USER_ID_FK = "user_id_fk";
//    private static final String COLUMN_VIDEO_URL = "video_url";
//
//
//    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +
//            "(" +
//            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//            COLUMN_FULL_NAME + " TEXT," +
//            COLUMN_USERNAME + " TEXT," +
//            COLUMN_PASSWORD + " TEXT" +
//            ")";
//
//    // Create an SQL statement for a playlist table
//    private static final String CREATE_TABLE_PLAYLISTS = "CREATE TABLE " + TABLE_PLAYLISTS +
//            "(" +
//            COLUMN_PLAYLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//            COLUMN_PLAYLIST_NAME + " TEXT," +
//            COLUMN_USER_ID_FK + " INTEGER," +
//            COLUMN_VIDEO_URL + " TEXT," +
//            "FOREIGN KEY (" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")" +
//            ")";
//
//    public Database(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create a user table and a playlist table
//        db.execSQL(CREATE_TABLE_USERS);
//        db.execSQL(CREATE_TABLE_PLAYLISTS);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // If the database version number changes, you can add the corresponding processing logic here
//    }
//
//    public boolean checkUser(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] columns = {COLUMN_USER_ID};
//        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
//        String[] selectionArgs = {username, password};
//        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
//        int count = cursor.getCount();
//        cursor.close();
//        db.close();
//        return count > 0;
//    }
//
//    public boolean addUser(String fullName, String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_FULL_NAME, fullName);
//        values.put(COLUMN_USERNAME, username);
//        values.put(COLUMN_PASSWORD, password);
//        long userId = db.insert(TABLE_USERS, null, values);
//        db.close();
//        if (userId != -1) {
//
//            return true;
//        } else {
//
//            return false;
//        }
//    }
//
//    @SuppressLint("Range")
//    public ArrayList<String> retrievePlaylist(String username) {
//        ArrayList<String> playlist = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // Query the user table to obtain the user ID
//        String queryUserId = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?";
//        Cursor cursorUserId = db.rawQuery(queryUserId, new String[]{username});
//
//        int userId = -1;
//
//        // Check the query result and get the user ID
//        if (cursorUserId != null && cursorUserId.moveToFirst()) {
//            userId = cursorUserId.getInt(cursorUserId.getColumnIndex(COLUMN_USER_ID));
//            cursorUserId.close();
//        }
//
//        // If a matching user ID is found, the playlist table is queried for the corresponding video URL
//        if (userId != -1) {
//            String queryPlaylist = "SELECT " + COLUMN_VIDEO_URL + " FROM " + TABLE_PLAYLISTS +
//                    " WHERE " + COLUMN_USER_ID_FK + " = ?";
//            Cursor cursorPlaylist = db.rawQuery(queryPlaylist, new String[]{String.valueOf(userId)});
//
//            // Iterate through the query results to add the video URL to the playlist
//            if (cursorPlaylist != null && cursorPlaylist.moveToFirst()) {
//                do {
//                    String videoUrl = cursorPlaylist.getString(cursorPlaylist.getColumnIndex(COLUMN_VIDEO_URL));
//                    playlist.add(videoUrl);
//                } while (cursorPlaylist.moveToNext());
//                cursorPlaylist.close();
//            }
//        }
//
//
//        db.close();
//
//        return playlist;
//    }
//
//    @SuppressLint("Range")
//    public void addUrlToPlaylist(String username, String videoUrl) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//
//        String queryUserId = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?";
//        Cursor cursorUserId = db.rawQuery(queryUserId, new String[]{username});
//
//        int userId = -1;
//
//
//        if (cursorUserId != null && cursorUserId.moveToFirst()) {
//            userId = cursorUserId.getInt(cursorUserId.getColumnIndex(COLUMN_USER_ID));
//            cursorUserId.close();
//        }
//
//
//        if (userId != -1) {
//            ContentValues values = new ContentValues();
//            values.put(COLUMN_PLAYLIST_NAME, "Default playlist"); // Here you can set the playlist name as you like
//            values.put(COLUMN_USER_ID_FK, userId);
//            values.put(COLUMN_VIDEO_URL, videoUrl);
//
//
//            long newRowId = db.insert(TABLE_PLAYLISTS, null, values);
//            if (newRowId != -1) {
//                // Insertion successful
//
//            } else {
//                System.exit(0);
//            }
//        }
//
//
//        db.close();
//    }
//}
//
//package com.example.task51c_subtask;
//
//        import android.content.Intent;
//        import android.content.SharedPreferences;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText usernameEditText, passwordEditText;
//    private Button loginButton, signUpButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        usernameEditText = findViewById(R.id.usernameEditText);
//        passwordEditText = findViewById(R.id.passwordEditText);
//        loginButton = findViewById(R.id.loginButton);
//        signUpButton = findViewById(R.id.signUpButton);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginUser();
//            }
//        });
//
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void loginUser() {
//        String username = usernameEditText.getText().toString().trim();
//        String password = passwordEditText.getText().toString().trim();
//        Database dbHelper = new Database(MainActivity.this);
//        boolean loginSuccessful = dbHelper.checkUser(username, password);
//        if (loginSuccessful) {
//
//            Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
//            intent.putExtra("username", username);
//            startActivity(intent);
//            finish();
//        } else {
//
//            Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
//package com.example.task51c_subtask;
//
//public class Playlist {
//    private String name;
//    private int userID;
//    private String videoURL;
//
//    public Playlist(String name, int userID, String videoURL) {
//        this.name = name;
//        this.userID = userID;
//        this.videoURL = videoURL;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }
//
//    public String getVideoURL() {
//        return videoURL;
//    }
//
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }
//}
//package com.example.task51c_subtask;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.AdapterView;
//        import android.widget.ArrayAdapter;
//        import android.widget.ListView;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import java.util.ArrayList;
//public class PlaylistActivity extends AppCompatActivity {
//    private ListView listViewPlaylist;
//    private ArrayAdapter<String> adapter;
//    private ArrayList<String> playlist;
//    private String username;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_playlist);
//
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            username = intent.getStringExtra("username");
//        }
//
//
//        listViewPlaylist = findViewById(R.id.listViewPlaylist);
//        playlist = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlist);
//        listViewPlaylist.setAdapter(adapter);
//
//
//        retrievePlaylistFromDatabase();
//
//        listViewPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String videoUrl = playlist.get(position);
//
//
//                Intent intent = new Intent(PlaylistActivity.this, youtubeActivity.class);
//                intent.putExtra("VIDEO_URL", videoUrl);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void retrievePlaylistFromDatabase() {
//        // Create an instance of the database helper class
//        Database db = new Database(PlaylistActivity.this);
//
//        // Get playlist data from the database
//        playlist = db.retrievePlaylist(username);
//
//        // Update the adapter to reflect the new playlist data
//        adapter.clear();
//        adapter.addAll(playlist);
//        adapter.notifyDataSetChanged();
//    }
//}
//package com.example.task51c_subtask;
//
//        import android.content.SharedPreferences;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//public class RegisterActivity extends AppCompatActivity {
//    private EditText fullNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
//    private Button registerButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        fullNameEditText = findViewById(R.id.fullNameEditText);
//        usernameEditText = findViewById(R.id.newUsernameEditText);
//        passwordEditText = findViewById(R.id.newPasswordEditText);
//        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
//        registerButton = findViewById(R.id.registerButton);
//
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                registerUser();
//            }
//        });
//    }
//
//    private void registerUser() {
//        String username = usernameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//        String fullName = fullNameEditText.getText().toString();
//        String confirmPassword = confirmPasswordEditText.getText().toString();
//
//        if (password.equals(confirmPassword)) {
//            Database db = new Database(RegisterActivity.this);
//            boolean registrationSuccessful = db.addUser(fullName, username, password);
//
//            if (registrationSuccessful) {
//                // The registration is successful, a success message is displayed and the registration page is closed
//                Toast.makeText(RegisterActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
//                finish(); // 关闭当前页面
//            } else {
//                // 注册失败，显示错误消息
//                Toast.makeText(RegisterActivity.this, "Registration failed, please try again", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "The password and confirmation password do not match", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
//
//package com.example.task51c_subtask;
//
//public class UserDatabase {
//    private String fullName;
//    private String username;
//    private String password;
//
//    public UserDatabase(String fullName, String username, String password) {
//        this.fullName = fullName;
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
//package com.example.task51c_subtask;
//
//
//        import android.content.Intent;
//
//        import android.os.Bundle;
//
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//
//
//public class VideoPlayerActivity extends AppCompatActivity {
//
//    private EditText urlEditText;
//    private String username;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_player);
//        Intent intent = getIntent();
//        if (intent != null) {
//            username = intent.getStringExtra("username");
//        }
//
//        urlEditText = findViewById(R.id.youtubeUrlEditText);
//        Button playButton = findViewById(R.id.playButton);
//        Button addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
//        Button viewPlaylistButton = findViewById(R.id.viewPlaylistButton);
//
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在这里处理播放按钮点击事件
//                String url = urlEditText.getText().toString().trim();
//                if (!url.isEmpty()) {
//                    // 实现播放逻辑
//                    Intent intent = new Intent(VideoPlayerActivity.this, youtubeActivity.class);
//                    intent.putExtra("VIDEO_URL", url);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(VideoPlayerActivity.this, "Please enter the video URL", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // 获取当前视频URL
//        addToPlaylistButton.setOnClickListener(v -> {
//            // 在这里处理添加到播放列表按钮点击事件
//            String url = urlEditText.getText().toString().trim();
//            if (!url.isEmpty()) {
//                // 实现添加到播放列表逻辑
//                Database db = new Database(VideoPlayerActivity.this);
//                db.addUrlToPlaylist(username, url);
//                Toast.makeText(VideoPlayerActivity.this, "The video URL has been added", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(VideoPlayerActivity.this, "Please enter the video URL", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        viewPlaylistButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在这里处理查询我的播放列表按钮点击事件
//                // 实现查询播放列表逻辑
//                Intent intent = new Intent(VideoPlayerActivity.this, PlaylistActivity.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//            }
//        });
//    }
//}
//
//package com.example.task51c_subtask;
//
//        import android.annotation.SuppressLint;
//        import android.os.Bundle;
//        import android.webkit.WebChromeClient;
//        import android.webkit.WebSettings;
//        import android.webkit.WebView;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//public class youtubeActivity extends AppCompatActivity {
//
//    private WebView webView;
//
//    @SuppressLint("SetJavaScriptEnabled")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//
//
//        webView = findViewById(R.id.webView);
//
//
//
//        // Enable JavaScript
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        String VIDEO_URL = getIntent().getStringExtra("VIDEO_URL");
//        String video = String.format("<iframe width=\"100%%\" height=\"100%%\" src=\" %s\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", VIDEO_URL);
//        webView.setWebChromeClient(new WebChromeClient());
//        // Load a YouTube web page or embed a video
//        webView.loadData(video,"text/html","utf-8");
//    }
//}
