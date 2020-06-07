package com.example.guesstheweapon.Data

import com.example.guesstheweapon.R

object DataGenerator {
    lateinit var weapons:ArrayList<NameSouns>

    fun getData():ArrayList<NameSouns>{
        weapons = arrayListOf(
            NameSouns("Groza", R.drawable.groza, R.raw.grozafullauto, R.raw.grozareload,  R.raw.grozafullautosuppressed),
            NameSouns("AWM", R.drawable.awm, R.raw.awmshot, R.raw.awmreload,  R.raw.suppressedawmshot),
            NameSouns("akm", R.drawable.akm, R.raw.akmfullauto, R.raw.akmreloading, R.raw.suppressedakmfullauto),
            NameSouns("m762", R.drawable.m762, R.raw.m762fullauto, R.raw.m762singlefire, R.raw.m762fullauto),
            NameSouns("M416", R.drawable.m416, R.raw.m416fullauto, R.raw.suppressedm416singlefire,R.raw.suppressedm416fullauto),
            NameSouns("m16A4", R.drawable.m16a4, R.raw.m16a4burstfire, R.raw.m16reloadrain, R.raw.suppressedm16a4burstfire),
            NameSouns("qbz", R.drawable.qbz, R.raw.qbzfullauto, R.raw.suppressedqbzsinglefire, R.raw.suppressedqbzfullauto),
            NameSouns("scar-L", R.drawable.scar, R.raw.scarfullauto, R.raw.scarreload, R.raw.suppressedscarfullauto),
            NameSouns("aug", R.drawable.aug, R.raw.augfullauto, R.raw.suppressedaugfullauto, R.raw.suppressedaugsinglefire),
            NameSouns("Mk47", R.drawable.mk47, R.raw.mk47burstfire, R.raw.suppressedmk47burstfire, R.raw.mk47singlefire),
            NameSouns("Kar98K", R.drawable.kar, R.raw.karshot, R.raw.karreloading, R.raw.suppressedkar),
            NameSouns("m24", R.drawable.m24, R.raw.m24shot, R.raw.m24reload, R.raw.suppressedm24shot),
            NameSouns("win94", R.drawable.win94, R.raw.win94shots, R.raw.win94reload, R.raw.win94shots),
            NameSouns("sks", R.drawable.sks, R.raw.sksshots, R.raw.sksreload, R.raw.suppressedskssemiauto),
            NameSouns("slr", R.drawable.slr, R.raw.slrsinglefire, R.raw.suppressedslrsinglefire, R.raw.suppressedslrsinglefire),
            NameSouns("vss", R.drawable.vss, R.raw.vssfullautorain, R.raw.vsssingleshot, R.raw.vssfullautorain),
            NameSouns("mini14", R.drawable.mini14, R.raw.mini14shots, R.raw.suppressedmini14singlefire, R.raw.suppressedmini14singlefire),
            NameSouns("mk14", R.drawable.mk14, R.raw.mk14fullauto, R.raw.mk14single, R.raw.suppressedmk14singlefire),
            NameSouns("qbu", R.drawable.qbu, R.raw.qbusinglefire, R.raw.suppressedqbusinglefire, R.raw.qbusinglefire),
            NameSouns("uzi", R.drawable.uzi, R.raw.uzifullauto, R.raw.uzisinglefire, R.raw.suppresseduzifullauto),
            NameSouns("UMP45", R.drawable.ump, R.raw.ump45fullauto, R.raw.ump45reload, R.raw.ump45surpressed),
            NameSouns("vector", R.drawable.vector, R.raw.vectorfullauto, R.raw.vectorsinglefire, R.raw.suppressedvectorfullauto),
            NameSouns("thompson", R.drawable.thompson, R.raw.thomsonfullauto, R.raw.thomsonsinglefire, R.raw.suppressedthomsonfullauto),
            NameSouns("s686", R.drawable.s686, R.raw.s686shots, R.raw.s686reload, R.raw.s686shots),
            NameSouns("s1897", R.drawable.s1897, R.raw.s1897shots, R.raw.s1897reloading, R.raw.s1897shots),
            NameSouns("S12K", R.drawable.s12k, R.raw.s12kshots, R.raw.suppresseds12k, R.raw.suppresseds12k),
            NameSouns("m249", R.drawable.m249, R.raw.m249fullauto, R.raw.reloadingm249, R.raw.reloadingm249),
            NameSouns("DP-28", R.drawable.dp_28, R.raw.dp28shots, R.raw.reloadingdp28, R.raw.dp28shots),
            NameSouns("p92", R.drawable.p92, R.raw.p92shots, R.raw.suppressedp92, R.raw.p92shots),
            NameSouns("p1911", R.drawable.p1911, R.raw.p1911shots, R.raw.suppressedp1911, R.raw.p1911shots),
            NameSouns("R195", R.drawable.r1895, R.raw.r1895shots, R.raw.suppressedr1895, R.raw.r1895shots),
            NameSouns("p18C", R.drawable.p18c, R.raw.p18cfullauto, R.raw.p18creload, R.raw.suppressedp18cfullauto),
            NameSouns("r45", R.drawable.r45, R.raw.r45multipleshots, R.raw.r45reload, R.raw.r45singleshots),
            NameSouns("Sawed-off", R.drawable.sawed_off, R.raw.sawedoffshots, R.raw.sawedoffreload, R.raw.sawedoffshots),
            NameSouns("Flare Gun", R.drawable.flare_gun, R.raw.shotflaregun, R.raw.shotflaregun, R.raw.flaregun),
            NameSouns("crossbow", R.drawable.crossbow, R.raw.crossbowshot, R.raw.crossbowreload, R.raw.crossbowshot),
            NameSouns("Skorpion", R.drawable.skorpion, R.raw.skorpionfullauto, R.raw.skorpionrealod, R.raw.skorpionsurpressed),
            NameSouns("G36C", R.drawable.g36c, R.raw.g36cfullaouto, R.raw.g36creload, R.raw.g36csurpressed),
            NameSouns("PP-19 Bizon", R.drawable.bizon, R.raw.bizonfullauto, R.raw.bizonreload, R.raw.bizonsurpressed),
            NameSouns("Desert Eagle", R.drawable.deagle, R.raw.deagleshots, R.raw.deaglereload, R.raw.deagleshots),
            NameSouns("DBS", R.drawable.dbs, R.raw.dbsshots, R.raw.dbsreload, R.raw.dbsshots),
            NameSouns("MP5K", R.drawable.mp5k, R.raw.m45pkfullauto, R.raw.m45pksurpressed, R.raw.m45pkfullauto)
        )

        return weapons
    }

}