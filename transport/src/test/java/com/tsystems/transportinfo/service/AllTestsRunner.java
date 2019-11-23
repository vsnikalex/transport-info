package com.tsystems.transportinfo.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CargoServiceImplUnitTests.class,
        TruckServiceImplUnitTests.class,
        GeoServiceImplUnitTests.class
})
public class AllTestsRunner {
    // This class remains empty, it is used only as a holder for the above annotations
}
