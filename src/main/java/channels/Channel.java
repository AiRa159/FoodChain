package channels;

import parties.Party;

import java.util.ArrayList;
import java.util.List;

public abstract class Channel {

    /**
     * List of channel participants
     */
    protected List<Party> parties;

    /**
     * Type of channel
     */
    private ChannelType type;

    /**
     * Constructor for creating a new channel
     * @param type
     */
    public Channel(ChannelType type) {
        this.parties = new ArrayList<>();
        this.type = type;
    }

    /**
     * @return channel type
     */
    public ChannelType getType() {
        return type;
    }

    /**
     * Add new participant
     * @param party
     */
    public void addParty(Party party){
        if(!parties.contains(party)){
            parties.add(party);
        }
    }

    /**
     * Remove participant
     * @param party
     */
    public void removeParty(Party party){
        if(parties.contains(party)){
            parties.remove(party);
        }
    }
}
