package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Depot;

public interface DepotDAO {
    Depot findDepotByCoords(GHPoint point);
}
