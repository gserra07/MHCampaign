
        var selectedCampaignIndex: Int by remember {
            mutableIntStateOf(0)
        }

        val monsterList = remember {
            mutableStateListOf<MonsterData>()

        }

        monsterList.add(MonsterData(Monster.GREAT_JAGRAS, 1, 2, 0))
        monsterList.add(MonsterData(Monster.PUKEI_PUKEI, 1, 1, 0))
        monsterList.add(MonsterData(Monster.ANJANATH, 1, 1, 0))

        val campaignList = mutableListOf(
            CampaignModel("Campaña 1", 2, 4, list = monsterList).id(0),
            CampaignModel("Campaña 2", 0, 0).id(1),
            CampaignModel("Campaña 3", 0, 0).id(2)
        )
        val hunterDataList = remember {
            mutableStateListOf<HunterData>()
        }

        hunterDataList.add(
            HunterData(
                "Ganexy", HunterWeapon.DUALBLADES, mutableListOf(
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.CARBALITE),
                    PartModel(PartItem.NERGIGANTE_REGROWTH_PLATE),
                    PartModel(PartItem.GREAT_JAGRAS_CLAW),
                )
            ).campaignId(0)
        )
        hunterDataList.add(HunterData("Adriatus", HunterWeapon.HEAVYBOWGUN).campaignId(0))
        hunterDataList.add(HunterData("Garatoth", HunterWeapon.SWITCHAXE).campaignId(0))
        hunterDataList.add(HunterData("Ingravitto", HunterWeapon.CHARGEBLADE))
        hunterDataList.add(HunterData("Guille", HunterWeapon.LONGSWORD).campaignId(0))
        hunterDataList.add(HunterData("SpiderWolf", HunterWeapon.BOW))

        MyDrawer(scope = scope,
            items = menuItems,
            selectedItem = selectedItem,
            drawerState = drawerState,
            content = {
                when (selectedItem.value.index) {
                    0 -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = {
                                FABCampaign(
                                    visible = newMonsterVisibility,
                                    campaignModel = campaignList[selectedCampaignIndex],
                                    onMonsterCreated = {
                                        campaignList[selectedCampaignIndex].addMonster(it)
                                    }
                                )
                            },
                            content = {
                                CampaignView(
                                    campaignList,
                                    hunterDataList,
                                    campaignViewModel = CampaignViewModel(
                                        campaignList,
                                        hunterDataList
                                    ),
                                    selectedCampaign = selectedCampaignIndex
                                ) {
                                    selectedCampaignIndex = it
                                }
                            })
                    }

                    1 -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = {
                                FABHunters(
                                    visible = newHunterVisibility,
                                    hunterDataList = hunterDataList,
                                    onHunterCreated = {
                                        createdHunter = it
                                        newHunterVisibility = false
                                    }
                                )
                            },
                            content = { HunterView(HuntersViewModel( hunterDataList)) })
                    }

                    2 -> {
                        val context = LocalContext.current
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {
                                Toast.makeText(context, "it.labe", Toast.LENGTH_SHORT).show()
                            },
                            onFloatingButtonContent = { MyfloatingPreview() },

                            content = { MHDropDownPreview() })
                    }

                    else -> {
                        MyScaffold(title = selectedItem.value.name,
                            scope = scope,
                            drawerState = drawerState,
                            onFloatingActionButtonClick = {},
                            onFloatingButtonContent = { MyfloatingPreview() },

                            content = { MyInventoryPreview() })

                    }
                }
            })
    }
}

@Composable
fun FABCampaign(
    visible: Boolean,
    campaignModel: CampaignModel,
    onMonsterCreated: (Monster) -> Unit
) {
    var newMonsterVisibility by remember {
        mutableStateOf(visible)
    }
    var createdMonster: MonsterData? by remember {
        mutableStateOf(null)
    }

    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 0, label = "Add Monster"
            ), MultiFabItem(
                id = 1, label = "Add Campaign"
            )
        ), fabIcon = FabIcon(
            iconRes = R.drawable.add_black, iconRotate = 45f
        ), onFabItemClicked = {
            when (it.id) {
                0 -> {
                    newMonsterVisibility = true
                }

                1 -> {

                }
            }
        }, fabOption = FabOption(
            iconTint = Color.White, showLabel = true
        )
    )
    campaignModel.monsterList.value?.map { it.monster }?.let {
        MonsterDialog(
            visibility = newMonsterVisibility,
            dataList = it.toMutableStateList(),
            onDismissListener = { newMonsterVisibility = false },
            onConfirmListener = { i, m ->
                onMonsterCreated(m)
                newMonsterVisibility = false
            }
        )
    }
}

@Composable
fun FABHunters(
    visible: Boolean,
    hunterDataList: MutableList<HunterData>,
    onHunterCreated: (HunterData) -> Unit
) {
    var newHunterVisibility by remember {
        mutableStateOf(visible)
    }
    var createdHunter: HunterData? by remember {
        mutableStateOf(null)
    }
    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 0, label = "Add Hunter"
            )
        ), fabIcon = FabIcon(
            iconRes = R.drawable.add_black, iconRotate = 45f
        ), onFabItemClicked = {
            when (it.id) {
                0 -> {
                    newHunterVisibility = true
                }
            }
        }, fabOption = FabOption(
            iconTint = Color.White, showLabel = true
        )
    )
    HunterDialog(visibility = newHunterVisibility,
        label = "New Hunter",
        data = null,
        onDismissListener = { newHunterVisibility = false },
        onConfirmListener = { item, index ->
            if (item != null) {
                Log.d("drawer", item.hunterName)
                createdHunter = item
                hunterDataList.add(item)
            }
            newHunterVisibility = false
        },
        onInventoryListener = { })
}