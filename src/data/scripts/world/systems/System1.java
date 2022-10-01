package data.scripts.world.systems;

import java.awt.Color;
import java.util.Random;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.MiscellaneousThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.RemnantSeededFleetManager;
import com.fs.starfarer.api.impl.campaign.procgen.themes.RemnantStationFleetManager;
import com.fs.starfarer.api.impl.campaign.procgen.themes.RemnantThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator.StarSystemData;
import com.fs.starfarer.api.impl.campaign.procgen.themes.RemnantThemeGenerator.RemnantSystemType;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import data.scripts.SSS_Utils;

public class System1 {
    public void generate(SectorAPI sector) {
        // Get character seed
        Random random = StarSystemGenerator.random;
        // Get star system
        StarSystemAPI system = sector.getStarSystem("system1");

        // Rename system with procedural name
        String systemName = SSS_Utils.generateProceduralName("star", system.getConstellation().getName());
        system.setBaseName(systemName);
        system.setName(systemName);

        // Create star for system
        PlanetAPI star = system.initStar(systemName.toLowerCase(), "star_orange", 750f, 400f, 10f, 05f, 3f);

        // Create custom entities
        float randomAngle1 = random.nextFloat() * 360f;
        SectorEntityToken commRelay = system.addCustomEntity(null, null, "comm_relay", "neutral");
        commRelay.setCircularOrbit(star, randomAngle1, 2000f, 200f);
        SectorEntityToken derelictCryosleeper = system.addCustomEntity(null, null, "derelict_cryosleeper", "neutral");
        derelictCryosleeper.setCircularOrbit(star, (randomAngle1 + 120f) % 360f, 2000f, 200f);
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint(null, "Inner System Jump-point");
        jumpPoint1.setStandardWormholeToHyperspaceVisual();
        jumpPoint1.setCircularOrbit(star, (randomAngle1 - 120f) % 360f, 2000f, 200f);
        system.addEntity(jumpPoint1);

        // Create planet 1
        String planet1Name = SSS_Utils.generateProceduralName("planet", star.getName());
        PlanetAPI planet1 = system.addPlanet(planet1Name.toLowerCase(), star, planet1Name, "barren_castiron", random.nextFloat() * 360f, 90f, 3000f, 300f);
        Misc.initConditionMarket(planet1);
        MarketAPI planet1Market = planet1.getMarket();
        planet1Market.addCondition("ore_sparse");
        planet1Market.addCondition("rare_ore_sparse");
        planet1Market.addCondition("no_atmosphere");
        planet1Market.addCondition("very_hot");

        // Create planet 2
        String planet2Name = SSS_Utils.generateProceduralName("planet", star.getName());
        PlanetAPI planet2 = system.addPlanet(planet2Name.toLowerCase(), star, planet2Name, "arid", random.nextFloat() * 360f, 130f, 4000f, 400f);
        Misc.initConditionMarket(planet2);
        MarketAPI planet2Market = planet2.getMarket();
        planet2Market.addCondition("farmland_poor");
        planet2Market.addCondition("ore_sparse");
        planet2Market.addCondition("organics_trace");
        planet2Market.addCondition("ruins_widespread");
        planet2Market.addCondition("habitable");
        planet2Market.addCondition("low_gravity");
        planet2Market.addCondition("hot");

        // Create asteroid belt 1
        String ring1Name = SSS_Utils.generateProceduralName("asteroid_belt", star.getName());
        system.addAsteroidBelt(star, 128, 5000f, 256f, 500f, 500f, "asteroid_belt", ring1Name);
        system.addRingBand(star, "misc", "rings_dust0", 256f, 3, Color.WHITE, 256f, 5000f, 500f);

        // Create planet 3
        String planet3Name = SSS_Utils.generateProceduralName("planet", star.getName());
        PlanetAPI planet3 = system.addPlanet(planet3Name.toLowerCase(), star, planet3Name, "barren-desert", random.nextFloat() * 360f, 90f, 6000f, 600f);
        Misc.initConditionMarket(planet3);
        MarketAPI planet3Market = planet3.getMarket();
        planet3Market.addCondition("ore_sparse");
        planet3Market.addCondition("rare_ore_sparse");
        planet3Market.addCondition("organics_trace");
        planet3Market.addCondition("thin_atmosphere");

        // Create planet 4
        String planet4Name = SSS_Utils.generateProceduralName("planet", star.getName());
        PlanetAPI planet4 = system.addPlanet(planet4Name.toLowerCase(), star, planet4Name, "gas_giant", random.nextFloat() * 360f, 250f, 7000f, 700f);
        Misc.initConditionMarket(planet4);
        MarketAPI planet4Market = planet4.getMarket();
        planet4Market.addCondition("volatiles_trace");
        planet4Market.addCondition("high_gravity");
        float planet4Radius = planet4.getRadius();
        SSS_Utils.createMagneticField(planet4, planet4Radius + 300f, (planet4Radius + 300f) / 2f, planet4Radius + 50f, planet4Radius + 300f, 1f);

        // Create custom entities
        float randomAngle2 = random.nextFloat() * 360f;
        SectorEntityToken stableLocation2 = system.addCustomEntity(null, null, "stable_location", "neutral");
        stableLocation2.setCircularOrbit(star, randomAngle2, 8000f, 800f);
        SectorEntityToken stableLocation3 = system.addCustomEntity(null, null, "stable_location", "neutral");
        stableLocation3.setCircularOrbit(star, (randomAngle2 + 120f) % 360f, 8000f, 800f);
        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint(null, "Fringe Jump-point");
        jumpPoint2.setStandardWormholeToHyperspaceVisual();
        jumpPoint2.setCircularOrbit(star, (randomAngle2 - 120f) % 360f, 8000f, 800f);
        system.addEntity(jumpPoint2);

        // Create asteroid belt 2
        String ring2Name = SSS_Utils.generateProceduralName("asteroid_belt", star.getName());
        system.addAsteroidBelt(star, 64, 9000f, 256f, 900f, 900f, "asteroid_belt", ring2Name);
        system.addRingBand(star, "misc", "rings_dust0", 256f, 3, Color.WHITE, 256f, 9000f, 900f);

        // Auto generate jump points
        system.autogenerateHyperspaceJumpPoints(true, false);

        // Clear nebula in hyperspace
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin)Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;
        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0f, radius + minRadius, 0f, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0f, radius + minRadius, 0f, 360f, 0.25f);

        // Generate custom entities
        MiscellaneousThemeGenerator theme = new MiscellaneousThemeGenerator();
        StarSystemData systemData = BaseThemeGenerator.computeSystemData(system);
        theme.addResearchStations(systemData, 0.25f, 1, 1, theme.createStringPicker(new Object[] {
            "station_research_remnant", Float.valueOf(10f) }));
        theme.addMiningStations(systemData, 0.25f, 1, 2, theme.createStringPicker(new Object[] {
            "station_mining_remnant", Float.valueOf(10f) }));
        theme.addShipGraveyard(systemData, 0.25f, 1, 1, theme.createStringPicker(new Object[] {
            "tritachyon", Float.valueOf(10f),
            "hegemony", Float.valueOf(7f),
            "independent", Float.valueOf(3f) }));
        theme.addDerelictShips(systemData, 0.25f, 1, 3, theme.createStringPicker(new Object[] {
            "tritachyon", Float.valueOf(10f),
            "hegemony", Float.valueOf(7f),
            "independent", Float.valueOf(3f) }));
        theme.addCaches(systemData, 0.25f, 1, 1, theme.createStringPicker(new Object[] {
            "weapons_cache_remnant", Float.valueOf(10f),
            "weapons_cache_small_remnant", Float.valueOf(10f),
            "supply_cache", Float.valueOf(10f),
            "supply_cache_small", Float.valueOf(10f),
            "equipment_cache", Float.valueOf(10f),
            "equipment_cache_small", Float.valueOf(10f) }));
        RemnantThemeGenerator.addBeacon(system, RemnantSystemType.DESTROYED);
        // Add dormant or active remnant fleets
        RemnantSeededFleetManager remnantFleets = new RemnantSeededFleetManager(system, 4, 8, 4, 8, 0.25f);
        system.addScript((EveryFrameScript)remnantFleets);
        // Add remnant station 1 that spawns remnant fleets
        float station1Radius = planet2.getRadius() + 150f;
        CampaignFleetAPI station1 = SSS_Utils.addAIBattlestation(planet2, false, station1Radius, station1Radius / 10f);
        RemnantStationFleetManager station1Fleets = new RemnantStationFleetManager((SectorEntityToken)station1, 1f, 0, 8, 25f, 4, 8);
        system.addScript((EveryFrameScript)station1Fleets);
        // Add remnant station 2 that spawns remnant fleets
        float station2Radius = planet4.getRadius() + 150f;
        CampaignFleetAPI station2 = SSS_Utils.addAIBattlestation(planet4, false, station2Radius, station2Radius / 10f);
        RemnantStationFleetManager station2Fleets = new RemnantStationFleetManager((SectorEntityToken)station2, 1f, 0, 8, 25f, 4, 8);
        system.addScript((EveryFrameScript)station2Fleets);
    }
}
