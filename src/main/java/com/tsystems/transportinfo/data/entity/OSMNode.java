package com.tsystems.transportinfo.data.entity;


import de.westnordost.osmapi.changesets.Changeset;
import de.westnordost.osmapi.map.data.LatLon;
import de.westnordost.osmapi.map.data.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OSMNode implements Node {

    private String id;

    private String lat;

    private String lon;

    private Map<String, String> tags;

    private String version;

    @Override
    public LatLon getPosition() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public Changeset getChangeset() {
        return null;
    }

    @Override
    public Date getDateEdited() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public long getId() {
        return Long.parseLong(this.id);
    }

    @Override
    public int getVersion() {
        return Integer.parseInt(this.version);
    }

}
