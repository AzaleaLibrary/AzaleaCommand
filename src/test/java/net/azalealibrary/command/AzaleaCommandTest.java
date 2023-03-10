package net.azalealibrary.command;

import be.seeseemelk.mockbukkit.MockBukkit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AzaleaCommandTest {

    private AzaleaCommand plugin;

    @BeforeEach
    public void setUp() {
        MockBukkit.mock();
        plugin = MockBukkit.load(AzaleaCommand.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void onLoad() {
//        plugin.getLogger().log(Level.INFO, "onLoad");
    }

    @Test
    void onEnable() {
//        plugin.getLogger().log(Level.INFO, "onEnable");
    }

    @Test
    void onDisable() {
//        plugin.getLogger().log(Level.INFO, "onDisable");
    }
}