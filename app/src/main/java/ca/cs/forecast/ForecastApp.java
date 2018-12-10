package ca.cs.forecast;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import ca.cs.forecast.model.WorldDatabase;
import ca.cs.forecast.utils.Constants;


public class ForecastApp extends Application {

    public static ForecastApp sInstance;

    private WorldDatabase mDatabase;

    public static ForecastApp get() {
        return sInstance;
    }

    public WorldDatabase getDB() {
        return mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = Room.databaseBuilder(getApplicationContext(), WorldDatabase.class, Constants.DATABASE_NAME)
                //TODO: .allowMainThreadQueries() doit OBLIGATOIREMENT être retiré.
                .allowMainThreadQueries()
                .build();

        sInstance = this;

        importDatabaseIfNeeded();
    }

    /**
     * If database is not created on device, creates it as file so that Room can use it
     */
    private void importDatabaseIfNeeded() {

        final File dbPath = getApplicationContext().getDatabasePath(Constants.DATABASE_NAME);

        if (dbPath.exists())
            return;

        dbPath.getParentFile().mkdirs();


        try {
            final InputStream inputStream = getApplicationContext().getAssets().open(Constants.DATABASE_NAME);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
