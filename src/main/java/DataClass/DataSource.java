package DataClass;

public interface DataSource {
    interface Callback {
        void onDataLoaded() throws Exception;
        void onDataFailed();
    }
}