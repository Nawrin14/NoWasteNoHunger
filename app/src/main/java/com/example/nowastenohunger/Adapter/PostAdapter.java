package com.example.nowastenohunger.Adapter;


/*
    PostAdapter Class was used to Make a RecyclerViewAdapter for making User's Post.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nowastenohunger.Activity.BottomNavigationMenuActivity;
import com.example.nowastenohunger.Activity.SmsActivity;
import com.example.nowastenohunger.Class.Post;
import com.example.nowastenohunger.Class.UserPost;
import com.example.nowastenohunger.Fragment.SearchDonationsFragment;
import com.example.nowastenohunger.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mContext;
    public List<Post>mPost;
    public List<String>imageList;

    String currentUserID, phoneNumberOfWhoPosted, messageToBeSent, locationOfWhoPosted, nameOfWhoPosted;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private  DatabaseReference databaseReference;

    public PostAdapter(Context mContext, List<Post> mPost, List<String> imageList) {
        this.mContext = mContext;
        this.mPost = mPost;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post, parent ,false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference =  FirebaseDatabase.getInstance().getReference("Users");
        Post post = mPost.get(position);
        String id ;
        id = firebaseUser.getUid();
        String imageURL = imageList.get(position);


        if(post.getPost().equals(""))
        {
            holder.description.setVisibility(View.GONE);
        }
        else
        {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(post.getPost());
            holder.time.setText(post.getTime());
            holder.fullname.setText(post.getfullname());
            holder.show_location.setText(post.getPostlocation());
            holder.contact.setText(post.getContact());
            //String loc = databaseReference.child("Users").child(imageURL).child("postlocation").toString();

           /* databaseReference.child("Users").child(imageURL).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()) {
                        String userAddress = String.valueOf(dataSnapshot.child("postlocation").getValue());

                        holder.show_location.setText(userAddress);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
            final String name = post.getfullname();
            final String location = post.getPostlocation();
            final String user_post = post.getPost();
            final String user_phone = post.getContact();
            final  String user_id = post.getUID();
            System.out.println(user_id);

            holder.donatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// The work of Buttons in post can be done here.
                    //Toast.makeText(mContext,/*"An SMS has been sent to your account."*/user_id,Toast.LENGTH_SHORT).show();
                try
                {
                    Intent i = new Intent(mContext, SmsActivity.class);
                    phoneNumberOfWhoPosted = user_phone;
                    messageToBeSent = user_post;
                    locationOfWhoPosted = location;
                    nameOfWhoPosted = name;
                    i.putExtra("p", phoneNumberOfWhoPosted);
                    i.putExtra("m", messageToBeSent);
                    i.putExtra("l", locationOfWhoPosted);
                    i.putExtra("n", nameOfWhoPosted);
                    i.putExtra("UID",user_id);
                    mContext.startActivity(i);
                    //databaseReference.child(user_id).child("post").removeValue();
                    //imageList.remove(position);
                    //mPost.remove(position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    //Toast.makeText(mContext,user_post+user_phone,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext,name,Toast.LENGTH_SHORT).show();
                }
            });

            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference profileReference = storageReference.child("Users/" + imageURL + "/ProfileImage.jpg");
            profileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(holder.profileImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

        //databaseReference =  FirebaseDatabase.getInstance().getReference("Users");

    }

    @Override
    public int getItemCount() {
        System.out.println(mPost.size());
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView fullname,description,time,show_location,contact;
        public ImageView profileImage;
        public Button donatebtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname =  itemView.findViewById(R.id.post_user);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.post_time);
            show_location=itemView.findViewById(R.id.post_location);
            profileImage = itemView.findViewById(R.id.post_profile_image);
            contact = itemView.findViewById(R.id.Contact);
            donatebtn = itemView.findViewById(R.id.donatebtn);
        }
    }


}
