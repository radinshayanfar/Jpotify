package jpotify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SongList implements Serializable {
    private static final long serialVersionUID = 6569839581000128744L;
    protected List<Song> songs = new ArrayList<>();
    protected Song current = null;

    public Song next(RepeatRule rule) {
        if (current == null) {
            if (songs.size() > 0)
                return current = songs.get(0);
            else return null;
        }
        if (rule == RepeatRule.REPEAT_ONE)
            return current;
        int index = songs.indexOf(current);
        if (index == songs.size() - 1) {
            if (rule == RepeatRule.REPEAT)
                return current = songs.get(0);
            else return current = null;
        } else {
            return current = songs.get(index + 1);
        }
    }

    public Song previous(RepeatRule rule) {
        if (current == null) {
            if (songs.size() > 0)
                return current = songs.get(songs.size() - 1);
            else return null;
        }
        if (rule == RepeatRule.REPEAT_ONE)
            return current;
        int index = songs.indexOf(current);
        if (index == 0) {
            if (rule == RepeatRule.REPEAT)
                return current = songs.get(songs.size() - 1);
            else return current = null;
        } else {
            return current = songs.get(index - 1);
        }
    }
}
