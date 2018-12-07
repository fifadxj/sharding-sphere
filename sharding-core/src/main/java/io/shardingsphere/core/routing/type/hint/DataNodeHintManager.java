package io.shardingsphere.core.routing.type.hint;

public class DataNodeHintManager {
    private static final ThreadLocal<DataNodeHintManager> INDEX_HINT_MANAGER = new ThreadLocal<DataNodeHintManager>() {
        protected DataNodeHintManager initialValue() {
            return new DataNodeHintManager();
        }
    };

    private DataNodeSelector dataNodeSelector;

    public static boolean hasHint() {
        return INDEX_HINT_MANAGER.get().dataNodeSelector != null;
    }

    public static DataNodeSelector getDataNodeSelector() {
        return INDEX_HINT_MANAGER.get().dataNodeSelector;
    }

    public static void setDataNodeSelector(DataNodeSelector dataNodeSelector) {
        INDEX_HINT_MANAGER.get().dataNodeSelector = dataNodeSelector;
    }

    public static void clear() {
        INDEX_HINT_MANAGER.remove();
    }
}
