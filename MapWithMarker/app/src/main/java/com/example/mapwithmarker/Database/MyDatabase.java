package com.example.mapwithmarker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

@Database(entities = {UserTable.class, ImageTable.class, ReviewTable.class}, version = 5)

public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao getDao();
    public abstract ImageDao getImageDao();
    public abstract ReviewDao getReviewDao();

    private static volatile MyDatabase INSTANCE;

   /* public static synchronized MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .build();
        }
        return INSTANCE;
    }*/

    public static synchronized MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .addMigrations(Migrations.MIGRATION_1_3)
                    .build();
        }
        return INSTANCE;
    }


    public void addAdminUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = getDao();
                if (!userDao.is_taken("admin")) { // Check if "admin" user already exists
                    UserTable adminUser = new UserTable(0, "admin", "admin", true,"0");//TODO
                    userDao.insertUser(adminUser);
                }
            }
        }).start();
    }

    public void addCity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageDao imageDao = getImageDao();
                if(!imageDao.is_taken("Paris")){
                    ImageTable imageTable = new ImageTable(0,"Paris","https://images.squarespace-cdn.com/content/v1/5e484ab628c78d6f7e602d73/373fca28-1c2f-4f0a-a0e4-747c07f7bd9f/La-rotonde-famous-paric-cafe-min.jpeg~https://www.my-webspot.com/upload/blog/195/article/_470x410_0038_the-best-activities-in-paris-and-the-attractions-to-enjoy.jpg~https://media.cntraveler.com/photos/58de89946c3567139f9b6cca/1:1/w_3633,h_3633,c_limit/GettyImages-468366251.jpg~https://cdn.britannica.com/35/155335-050-D0C61BB7/Notre-Dame-de-Paris-France.jpg~https://www.marina-de-paris.com/wp-content/uploads/2019/07/img.jpg.webp" );
                    imageDao.insertCity(imageTable);
                }
                if(!imageDao.is_taken("Lyon")){
                    ImageTable imageTable = new ImageTable(0,"Lyon","https://static.ffx.io/images/$zoom_1%2C$multiply_0.8122%2C$ratio_1.777778%2C$width_985%2C$x_15%2C$y_61/t_crop_custom/q_86%2Cf_auto/a3ec14d3496e6e30cd40296e76a157a2def33493~https://upload.wikimedia.org/wikipedia/commons/a/a2/MBA_Lyon_facade_nuit.jpg~https://a.cdn-hotels.com/gdcs/production86/d581/06c2deb4-827a-4f74-9ffd-54817807e0fd.jpg?impolicy=fcrop&w=1600&h=1066&q=medium~https://a.cdn-hotels.com/gdcs/production172/d251/f15ada23-c0bf-460d-a0d7-0ce1f3eb43b7.jpg~https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2c/03/3b/17/caption.jpg?w=1100&h=-1&s=1");
                    imageDao.insertCity(imageTable);
                }
                if(!imageDao.is_taken("Toulouse")){
                    ImageTable imageTable = new ImageTable(0,"Toulouse","https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_396,q_75,w_704/v1/crm/houston/Toulhouse0_88e16cb5-ebab-366b-f6b3969feaa3fd4c.jpg~https://www.gpsmycity.com/img/gd_sight/32522.jpg~https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Toulouse_Capitole_Night_Wikimedia_Commons.jpg/800px-Toulouse_Capitole_Night_Wikimedia_Commons.jpg~https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Fa%C3%A7ade_de_la_cath%C3%A9drale_Saint-%C3%89tienne_de_Toulouse.jpg/640px-Fa%C3%A7ade_de_la_cath%C3%A9drale_Saint-%C3%89tienne_de_Toulouse.jpg~https://a.cdn-hotels.com/gdcs/production191/d1711/f8850560-7c26-4a90-9617-bc3317f8d815.jpg");
                    imageDao.insertCity(imageTable);
                }
                if(!imageDao.is_taken("Rennes")){
                    ImageTable imageTable = new ImageTable(0,"Rennes","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0e/cb/d7/d1/facade.jpg?w=1200&h=-1&s=1~https://upload.wikimedia.org/wikipedia/commons/5/57/Rennes-ancien_Palais_Universitaire-Mus%C3%A9e_des_beaux_arts.JPG~https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Fa%C3%A7ade_de_l%27h%C3%B4tel_de_ville%2C_Rennes%2C_France.jpg/1200px-Fa%C3%A7ade_de_l%27h%C3%B4tel_de_ville%2C_Rennes%2C_France.jpg~https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Cathedral_Rennes.jpg/800px-Cathedral_Rennes.jpg~https://dynamic-media-cdn.tripadvisor.com/media/photo-o/04/0d/5c/8c/parc-du-thabor.jpg?w=1200&h=-1&s=1");
                    imageDao.insertCity(imageTable);
                }
                if(!imageDao.is_taken("Strasbourg")){
                    ImageTable imageTable = new ImageTable(0,"Strasbourg","https://media-cdn.tripadvisor.com/media/photo-s/14/ef/13/06/terrasse.jpg~https://upload.wikimedia.org/wikipedia/commons/8/88/Le_Palais_des_Rohans.jpg~https://upload.wikimedia.org/wikipedia/commons/d/db/%C3%89glise_r%C3%A9form%C3%A9e_Saint-Paul_%40_Strasbourg_%2844894771264%29.jpg~https://i.pinimg.com/originals/42/9e/a4/429ea404dd8c4b74d78e97e750cf8aac.jpg~https://imageio.forbes.com/blogs-images/geoffreymorrison/files/2018/10/Exploring-Petite-France-Strasbourg-by-Geoffrey-Morrison-3-of-10.jpg?height=444&width=711&fit=bounds");
                    imageDao.insertCity(imageTable);
                }

            }
        }).start();

    }

    public void addReview() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ReviewDao reviewDao = getReviewDao();
                if(!reviewDao.is_taken("Kim")) {
                    ReviewTable reviewTable = new ReviewTable(0, "Kim", "avis");
                    reviewDao.insertReview(reviewTable);
                }

            }
        }).start();

    }

}
/*
https://media-cdn.tripadvisor.com/media/photo-s/14/ef/13/06/terrasse.jpg~https://upload.wikimedia.org/wikipedia/commons/8/88/Le_Palais_des_Rohans.jpg~https://upload.wikimedia.org/wikipedia/commons/d/db/%C3%89glise_r%C3%A9form%C3%A9e_Saint-Paul_%40_Strasbourg_%2844894771264%29.jpg~https://i.pinimg.com/originals/42/9e/a4/429ea404dd8c4b74d78e97e750cf8aac.jpg~https://imageio.forbes.com/blogs-images/geoffreymorrison/files/2018/10/Exploring-Petite-France-Strasbourg-by-Geoffrey-Morrison-3-of-10.jpg?height=444&width=711&fit=bounds
 */