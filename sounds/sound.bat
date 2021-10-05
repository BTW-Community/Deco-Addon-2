:: Newer versions of vanilla use hashed files to store sounds
:: However, the data is still stored with the .ogg file format and appropriate codec
:: This script copies the sounds over into a file and folder structure which 1.5.2 is able to recognize
::	and adds the requisite .ogg file extension

echo "Retrieving sounds..."

CD \D %~dp0
if not exist resources\sound3\deco (
	mkdir resources\sound3\deco
)

if not exist resources\sound3\deco\dig (
	mkdir resources\sound3\deco\dig
)

if not exist resources\sound3\deco\step (
	mkdir resources\sound3\deco\step
)

copy assets\objects\b2\b2a4d2dcfde676caf4e6b3d59848901b2a9441ce resources\sound3\deco\dig\chain1.ogg
copy assets\objects\cc\cc08e21b571415e27b1e70eed6a488f8ba9b3db6 resources\sound3\deco\dig\chain2.ogg
copy assets\objects\6b\6bc3dcea8da81eb0c892f7c00c84accbad8ef2f7 resources\sound3\deco\dig\chain3.ogg
copy assets\objects\12\12965f48483c25d58bad1950e55cfcc77eb14364 resources\sound3\deco\dig\chain4.ogg

copy assets\objects\05\057ab504f5f407ecca355b4b79a0e1c1f75060cf resources\sound3\deco\step\chain1.ogg
copy assets\objects\57\574374a17842b530c5932bea1f13066aeeb9c382 resources\sound3\deco\step\chain2.ogg
copy assets\objects\da\da72608522eaea87e6ac5a79d4d2eec8d04eff15 resources\sound3\deco\step\chain3.ogg
copy assets\objects\e3\e3e7e9911e1d24f0225bc565810a58ea81c70f77 resources\sound3\deco\step\chain4.ogg
copy assets\objects\87\873f73538c8ea67b86f5edf642db8a1c8bbfadd0 resources\sound3\deco\step\chain5.ogg
copy assets\objects\85\85b43aa402a60adf087632be25d35a4e3c354ca3 resources\sound3\deco\step\chain6.ogg

copy assets\objects\2e\2efdaa5a89ae6a68ceddf60aa91a4b410f0f3e58 resources\sound3\deco\dig\lantern1.ogg
copy assets\objects\44\443a6c542e6cb96d8d7d6e185623266ce0c3af69 resources\sound3\deco\dig\lantern2.ogg
copy assets\objects\cc\cc62eb000f8789c06cec01bfe9bf9b989b015171 resources\sound3\deco\dig\lantern3.ogg
copy assets\objects\fb\fbc806cf8ce69a93ddc2b0b6a01df3c94cbfda87 resources\sound3\deco\dig\lantern4.ogg
copy assets\objects\94\94f85a27c377b0ef874ebe810292c9c77a7be1f9 resources\sound3\deco\dig\lantern5.ogg
copy assets\objects\78\78f33e68a3142032e0a1b0c95012f4bcb2ea3ea9 resources\sound3\deco\dig\lantern6.ogg

copy assets\objects\66\66a7ad307e66d55be39ad129d5dde4a139c6decb resources\sound3\deco\step\lantern1.ogg
copy assets\objects\23\236d6b2e2841e6eeaff442cf4599ac8f1d0c87bf resources\sound3\deco\step\lantern2.ogg
copy assets\objects\91\913db11b61e0c079114e702507dd33fd59846c2c resources\sound3\deco\step\lantern3.ogg
copy assets\objects\79\79ae36927d7d5e1d4210debb5e98c0a8d0e4ba39 resources\sound3\deco\step\lantern4.ogg
copy assets\objects\39\3920b92ee14834623308f557d4ac912aef760108 resources\sound3\deco\step\lantern5.ogg
copy assets\objects\85\85462af1c6ccc02b19989be398b0bcc488b249f1 resources\sound3\deco\step\lantern6.ogg

copy assets\objects\6a\6a4796fec25e83aa2d0151f0ddd86e6ce86bb48a resources\sound3\deco\dig\netherrack1.ogg
copy assets\objects\49\49d5826555c9f00e36c1e2e0386118f4e0a27c4f resources\sound3\deco\dig\netherrack2.ogg
copy assets\objects\50\5066b310fd288cf443497dd362ff151923525213 resources\sound3\deco\dig\netherrack3.ogg
copy assets\objects\1d\1d488970ff2dd9d19a397c8bd593b82bd061b655 resources\sound3\deco\dig\netherrack4.ogg
copy assets\objects\b2\b2eff3be4927ad66e197d91699609bd354320bef resources\sound3\deco\dig\netherrack5.ogg
copy assets\objects\c5\c57a387a15458219f9bbc02be71a61bdb49bf214 resources\sound3\deco\dig\netherrack6.ogg

copy assets\objects\19\198ab0290fa4593eba3327588f80859470204392 resources\sound3\deco\step\netherrack1.ogg
copy assets\objects\96\96d059f410a8aadf3107caf4a207b14f285d0e43 resources\sound3\deco\step\netherrack2.ogg
copy assets\objects\cc\ccd1b84268f17f117de706616172b96c7fb61168 resources\sound3\deco\step\netherrack3.ogg
copy assets\objects\69\690b4c1c0ff9d2fe28e407b283d7617994a0c78c resources\sound3\deco\step\netherrack4.ogg
copy assets\objects\e7\e7f9df4bc20f09a57f6ffcf224031427594037cb resources\sound3\deco\step\netherrack5.ogg
copy assets\objects\9a\9a5e300fab1421f481c01aa03f4b6148a221f917 resources\sound3\deco\step\netherrack6.ogg

copy assets\objects\76\7690c52b7190d7e63681d868bba510f9c9301b7a resources\sound3\deco\dig\netherbrick1.ogg
copy assets\objects\fe\fe6802230737802d680dfcefef033c23a0f7f085 resources\sound3\deco\dig\netherbrick2.ogg
copy assets\objects\7c\7c0780f28fd89565666f69131e98ca72aa739c05 resources\sound3\deco\dig\netherbrick3.ogg
copy assets\objects\03\038aed8ef08d5f6eb01712b843cb5e128463efe1 resources\sound3\deco\dig\netherbrick4.ogg
copy assets\objects\ed\ed14d4e785bbbedbef7e85d57b7108ddc630a678 resources\sound3\deco\dig\netherbrick5.ogg
copy assets\objects\84\8478523f063bb2f3daba313c8b7a9e91e117f236 resources\sound3\deco\dig\netherbrick6.ogg

copy assets\objects\96\9673ae2933b66eada687763cb7f57fa768f30078 resources\sound3\deco\step\netherbrick1.ogg
copy assets\objects\f8\f88cf5e49d0eccee19ada047b57f856efc87d06b resources\sound3\deco\step\netherbrick2.ogg
copy assets\objects\7f\7fa3f348f4cc91c9756f333308608fd7cf4a023f resources\sound3\deco\step\netherbrick3.ogg
copy assets\objects\5e\5e7800f8ca54ee32cf7c603206b174298db3d680 resources\sound3\deco\step\netherbrick4.ogg
copy assets\objects\a9\a92e020da7bc97d1c05711b5b3b318a55bd5811f resources\sound3\deco\step\netherbrick5.ogg
copy assets\objects\76\7690c52b7190d7e63681d868bba510f9c9301b7a resources\sound3\deco\step\netherbrick6.ogg

copy assets\objects\e1\e1531690171d1940e0737074a8e6dbd31387d4b1 resources\sound3\deco\dig\bone1.ogg
copy assets\objects\3e\3eddefcae7dd754b80765c2fee955aa26c644e7c resources\sound3\deco\dig\bone2.ogg
copy assets\objects\b2\b2ae2bdcbaabf46448ed282df1ae1cc7096bb8e9 resources\sound3\deco\dig\bone3.ogg
copy assets\objects\de\de98f91fbce73a38fa30e887ac8dce08aac8e675 resources\sound3\deco\dig\bone4.ogg
copy assets\objects\0a\0ae403bea99c54d26b09f7d588ea11ddb4b6dc98 resources\sound3\deco\dig\bone5.ogg

copy assets\objects\49\49ae543155e15b8115206f3bdcf1b929b7725b62 resources\sound3\deco\step\bone1.ogg
copy assets\objects\e2\e2c1207fb138a72cbc29277497408cde75b28698 resources\sound3\deco\step\bone2.ogg
copy assets\objects\a5\a5362a693f9ae184c1e87ac07fb7ad949d2aee45 resources\sound3\deco\step\bone3.ogg
copy assets\objects\11\11b24be2acd87d0d3cadd3b4a5e7f5cf29d75b9c resources\sound3\deco\step\bone4.ogg
copy assets\objects\d6\d6c41fbec35949f5ab7423d0a3cd174bacfd406d resources\sound3\deco\step\bone5.ogg

copy assets\objects\92\929a37884fd323fafdd622aba0fb31822c4b937e resources\sound3\deco\dig\soulsand1.ogg
copy assets\objects\7a\7a37e50b5e9dda4f8fbb539708048602829bdf2e resources\sound3\deco\dig\soulsand2.ogg
copy assets\objects\f1\f16bb0c16c52f299e5a24a59623cdbf53f981bd8 resources\sound3\deco\dig\soulsand3.ogg
copy assets\objects\f2\f27a55328d5788be68e44971b39882b356650e5f resources\sound3\deco\dig\soulsand4.ogg
copy assets\objects\7d\7de020ee7a67020160fab449f00a9d268e16f28e resources\sound3\deco\dig\soulsand5.ogg
copy assets\objects\b2\b268b067c79a2701e82e7c7c7ca11459c4266fe0 resources\sound3\deco\dig\soulsand6.ogg
copy assets\objects\25\25fd44762080eefc97b3521c345a702e51e6526e resources\sound3\deco\dig\soulsand7.ogg
copy assets\objects\64\6481741be0ceaac32c6aa64f6a1db9daa737e02a resources\sound3\deco\dig\soulsand8.ogg
copy assets\objects\12\12e09921944cc975b813eee133b0a1537a10eadb resources\sound3\deco\dig\soulsand9.ogg

copy assets\objects\14\14647c8f94b4bc8c7e891a9dca105bdd75a6b87e resources\sound3\deco\step\soulsand1.ogg
copy assets\objects\80\807eb75a38ac486e9223de57297a992aaf0de6ff resources\sound3\deco\step\soulsand2.ogg
copy assets\objects\47\47114728c39caacaf6f5fa07b7111f2643747409 resources\sound3\deco\step\soulsand3.ogg
copy assets\objects\c3\c328c36b1be22ea6afe6365806f24e0b3a3f3117 resources\sound3\deco\step\soulsand4.ogg
copy assets\objects\1e\1ec6fc7f6d86ad5bd72d8f64ee5e4425f9af67a5 resources\sound3\deco\step\soulsand5.ogg

copy assets\objects\d1\d10147b5114fb55a1d3a2705cef3022ea59e0cc3 resources\sound3\deco\dig\soulsteel1.ogg
copy assets\objects\00\00c4daa73f67f90bb994c0009dd35d02a3c413b4 resources\sound3\deco\dig\soulsteel2.ogg
copy assets\objects\54\546fddb67d3e27c613d236d210baef4224c3dbc5 resources\sound3\deco\dig\soulsteel3.ogg
copy assets\objects\ce\ce45c82f140e19a9688aebb53afc61e9664bb735 resources\sound3\deco\dig\soulsteel4.ogg

copy assets\objects\73\73d282fd17857eba131e7150f3f7a2fb1bf30c5a resources\sound3\deco\step\soulsteel1.ogg
copy assets\objects\43\43e15f44cf8c5e02591246685e968e0612aa1dd4 resources\sound3\deco\step\soulsteel2.ogg
copy assets\objects\cd\cdd074345be86d82cc9abe81b73ff7d6e2aaa6f9 resources\sound3\deco\step\soulsteel3.ogg
copy assets\objects\e2\e24784aa709d50af48bfe4d6e49bb423b22227aa resources\sound3\deco\step\soulsteel4.ogg
copy assets\objects\55\556e7d997332a802ac7432853f9b0e46aefc0ea8 resources\sound3\deco\step\soulsteel5.ogg
copy assets\objects\4a\4a7ddf2a0a3e52ed407a37fe4e021212eea3ef39 resources\sound3\deco\step\soulsteel6.ogg

copy assets\objects\8d\8d3cd8ca91c60d4566ef00acc1ad6c9e580224aa resources\sound3\deco\step\vine1.ogg
copy assets\objects\1c\1c34854baa9fdf7a74fb4553890d347050ca7514 resources\sound3\deco\step\vine2.ogg
copy assets\objects\5d\5d19075995133236a29995f9cb1929294e3df59e resources\sound3\deco\step\vine3.ogg
copy assets\objects\28\2868b5ea245c44da91f34dd7ff97b3aba1eccbc6 resources\sound3\deco\step\vine4.ogg
copy assets\objects\8c\8c931b5195bf445110f54b24fd6c8f6ff9c89213 resources\sound3\deco\step\vine5.ogg

copy assets\objects\b7\b7b016e6698ecfa731e978dc2d5d335404cfba9b resources\sound3\deco\dig\bloodLog1.ogg
copy assets\objects\95\95c268c06860403f9e1584722ae1d191ac1dd373 resources\sound3\deco\dig\bloodLog2.ogg
copy assets\objects\b5\b5fae0ae79af36a80d94117048d8f7f690af2064 resources\sound3\deco\dig\bloodLog3.ogg
copy assets\objects\33\332f7c605dd41cf24080ee8c258f9361f32bd9ff resources\sound3\deco\dig\bloodLog4.ogg
copy assets\objects\42\42d0cf9e4bc25a24613d591fc725718c743fa14e resources\sound3\deco\dig\bloodLog5.ogg
copy assets\objects\ab\abd419199748855d0c217facdb02cfad1d3a610a resources\sound3\deco\dig\bloodLog6.ogg

copy assets\objects\e3\e3b2702fca190cb04f9a8c20b0c371e9678dc6bb resources\sound3\deco\step\bloodLog1.ogg
copy assets\objects\4e\4e78551d06e0e7c120586d9e3dbdbe735e937915 resources\sound3\deco\step\bloodLog2.ogg
copy assets\objects\b4\b4d5cc3cd369efec7b0b1d27b9fd5153905a591a resources\sound3\deco\step\bloodLog3.ogg
copy assets\objects\d9\d95a39ddb127bdf9bba43a3981e8b3e6b93e28b2 resources\sound3\deco\step\bloodLog4.ogg
copy assets\objects\11\11a0ce995bcc34a60df6eeeedae613b20a30213f resources\sound3\deco\step\bloodLog5.ogg
copy assets\objects\f7\f72d7ae413b12a0d05e39d5911006af721521f61 resources\sound3\deco\step\bloodLog6.ogg

copy assets\objects\a9\a9869bcdaa5a3363b8404d67c9eb9cc5ec6c3315 resources\sound3\deco\dig\groth1.ogg
copy assets\objects\5c\5cca35534cc2ee3529d39b7ccc12b437955e0683 resources\sound3\deco\dig\groth2.ogg
copy assets\objects\5d\5d847e679f297ce71258c6724d1a3840362dde7d resources\sound3\deco\dig\groth3.ogg
copy assets\objects\5e\5e971b2a4881d40d771da3e6798ebc423ea4bf23 resources\sound3\deco\dig\groth4.ogg
copy assets\objects\f1\f132de03fd665eeaa52033791b20e8fb0f229c73 resources\sound3\deco\dig\groth5.ogg
copy assets\objects\5c\5cfda81340c5acd35ac94bfd0b908579d05d8770 resources\sound3\deco\dig\groth6.ogg

copy assets\objects\62\627986868ad27e85f53e1e8dc1db406fe2d0a7b2 resources\sound3\deco\step\groth1.ogg
copy assets\objects\64\64397c108f51882304e4012219b0782bbadb6056 resources\sound3\deco\step\groth2.ogg
copy assets\objects\59\595b687c4da88dfafee324b6c500e1df7dead0eb resources\sound3\deco\step\groth3.ogg
copy assets\objects\de\de7b3af61600660442e2ad6af224d48b7bf37030 resources\sound3\deco\step\groth4.ogg
copy assets\objects\61\6164117445e8dfd29379e9bb97108ec0b7126270 resources\sound3\deco\step\groth5.ogg

if not exist resources\sound3\deco\random (
	mkdir resources\sound3\deco\random
)

copy assets\objects\87\871634413de37a092d0e5fe7844a37917c27bbf2 resources\sound3\deco\random\doorClose1.ogg
copy assets\objects\6e\6e0d43b064fb69f7b63e0704d99a38bb2ebc19e0 resources\sound3\deco\random\doorClose2.ogg
copy assets\objects\d1\d11ce21cc8a280448e9c12a28406583127809b79 resources\sound3\deco\random\doorClose3.ogg
copy assets\objects\01\011cf0d5b4015b52129caa98c7c4a35ea7a795ed resources\sound3\deco\random\doorClose4.ogg
copy assets\objects\4c\4cf6992a99518dfac836228cffbbc7e8344d969b resources\sound3\deco\random\doorClose5.ogg
copy assets\objects\73\73a3bfcb37099c49ac22e6fa308ca14a6e4f8347 resources\sound3\deco\random\doorClose6.ogg

copy assets\objects\4e\4ed1f29f3e1378811f7fcc49db743400b8e3284f resources\sound3\deco\random\doorOpen1.ogg
copy assets\objects\5c\5c44e7a8da8afddd9220121ba76ec1badebb56c3 resources\sound3\deco\random\doorOpen2.ogg
copy assets\objects\d2\d2ba0797ca24fcf15d105c88899089e4af5cb9f4 resources\sound3\deco\random\doorOpen3.ogg
copy assets\objects\21\21464f4cfb0163f9071d2a84d818bd5ac5b6eee6 resources\sound3\deco\random\doorOpen4.ogg

copy assets\objects\10\108c61bfecd4f969778173578984503e08dd7898 resources\sound3\deco\random\doorIronClose1.ogg
copy assets\objects\ae\aec962400204953cc7eefef2c0cc1942bbeaff38 resources\sound3\deco\random\doorIronClose2.ogg
copy assets\objects\27\278ae35982e186ab9f4a9ef5728b18793c939e98 resources\sound3\deco\random\doorIronClose3.ogg
copy assets\objects\47\4710afcb488c6ee3881e3b896dfcd5cc2069049a resources\sound3\deco\random\doorIronClose4.ogg

copy assets\objects\a8\a8756192499be8204a5e195b9873b6b48c0d1d25 resources\sound3\deco\random\doorIronOpen1.ogg
copy assets\objects\02\0208597870daf05e8bde08d4aac04989c9ebedf2 resources\sound3\deco\random\doorIronOpen2.ogg
copy assets\objects\e7\e700e2c9abdfc27a32ac3b7f759d6c48a0846992 resources\sound3\deco\random\doorIronOpen3.ogg
copy assets\objects\ae\ae6914ce7180755e98ee2d169e1757bc624bbade resources\sound3\deco\random\doorIronOpen4.ogg

copy assets\objects\15\159697af99ef2640f870bee2aaeda9170a918523 resources\sound3\deco\random\trapdoorClose1.ogg
copy assets\objects\5e\5e49444266bf88c2006233a811561272ad8f157f resources\sound3\deco\random\trapdoorClose2.ogg
copy assets\objects\c9\c9a5510420292be670d0020b7123ca0fef1f6087 resources\sound3\deco\random\trapdoorClose3.ogg

copy assets\objects\9e\9eb0c3c8c34bcf83db451c12f24edd756908d265 resources\sound3\deco\random\trapdoorOpen1.ogg
copy assets\objects\a4\a410f82debee252f1399a8c391bef261ab42d07c resources\sound3\deco\random\trapdoorOpen2.ogg
copy assets\objects\37\37c0ff8ac56889125bb0d11be7225f453e1e4098 resources\sound3\deco\random\trapdoorOpen3.ogg
copy assets\objects\f7\f756c39f4ca9565c359a66d33b5a4f9d886e5479 resources\sound3\deco\random\trapdoorOpen4.ogg

copy assets\objects\aa\aaebcc83934fb7919c7af934eefc5a586678e1b2 resources\sound3\deco\random\gateClose1.ogg
copy assets\objects\58\58d63232621aaa03e4d09598700561214e7c2477 resources\sound3\deco\random\gateClose2.ogg

copy assets\objects\fa\fa68bbdcbb68db8dea8604f3ae5caadb9ded0cbe resources\sound3\deco\random\gateOpen1.ogg
copy assets\objects\83\836ae33bdfadbe5dcbe0696fe64c671beb4938fb resources\sound3\deco\random\gateOpen2.ogg

copy assets\objects\a7\a7d0d6f761c9a647cc0273bc016f2772b85f6d88 resources\sound3\deco\random\chestClose1.ogg
copy assets\objects\ea\eaa2cfc2ca71f219930cc496d5316853364d3623 resources\sound3\deco\random\chestClose2.ogg
copy assets\objects\fd\fda6f8d1f1daabad0fd78903cd3c4b366f77a8e5 resources\sound3\deco\random\chestClose3.ogg

copy assets\objects\f9\f993dbcc8cdde669020b9f988f091d997006ce8b resources\sound3\deco\random\chestOpen1.ogg

copy assets\objects\42\42b2964c08f50be3fda62257202efe42f262c005 resources\sound3\deco\random\strip1.ogg
copy assets\objects\38\38044a5747fd72dc26f3c0a37bef44ffa3744078 resources\sound3\deco\random\strip2.ogg
copy assets\objects\a8\a84dafa90faa56556346437e5f27ad047dc584ea resources\sound3\deco\random\strip3.ogg
copy assets\objects\76\7621881ced7901c92236f386c26cd678aaf9ba49 resources\sound3\deco\random\strip4.ogg

copy assets\objects\e5\e528a3ccb3b90e3a2eb075b69a3663635b05d1d6 resources\sound3\deco\random\pumpkinCarve1.ogg
copy assets\objects\19\1997fd410166b4e52ede02d23fb8947306f8630d resources\sound3\deco\random\pumpkinCarve2.ogg

copy assets\objects\0e\0e6696ec35c5f4982cad6a6731edcffb11728aa9 resources\sound3\deco\random\till1.ogg
copy assets\objects\46\46dd1e5e0f90bb72261e2986d530e80e8fc50560 resources\sound3\deco\random\till2.ogg
copy assets\objects\d7\d75f19c657e1f22b47d2060fd3495c444f35a477 resources\sound3\deco\random\till3.ogg
copy assets\objects\cb\cb95637a9d5e9b0cb36a2516f0dfac30fed9d720 resources\sound3\deco\random\till4.ogg

if not exist resources\sound3\deco\mob (
	mkdir resources\sound3\deco\mob
)

if not exist resources\sound3\deco\mob\squid (
	mkdir resources\sound3\deco\mob\squid
)

copy assets\objects\8e\8ea4cfd31d4f286b039a63d52020aa6b5408a763 resources\sound3\deco\mob\squid\say1.ogg
copy assets\objects\4e\4ed7023a970b7f6a6d03558c3125fe85fea4ecd8 resources\sound3\deco\mob\squid\say2.ogg
copy assets\objects\d9\d997d6bd0a18be2b62c27363878f99540d845825 resources\sound3\deco\mob\squid\say3.ogg
copy assets\objects\4a\4a0ce0713583399108fad4698cbe63714fe5898f resources\sound3\deco\mob\squid\say4.ogg
copy assets\objects\b2\b212815d8728730de16ff65a3f54a7fd06b98fbc resources\sound3\deco\mob\squid\say5.ogg

copy assets\objects\68\68beeda338fdb43c3b7cbbb718e82839e6d07ef3 resources\sound3\deco\mob\squid\death1.ogg
copy assets\objects\a9\a91e1f2cba408d784538d21424e52253864534b6 resources\sound3\deco\mob\squid\death2.ogg
copy assets\objects\3e\3efb22bf9a4ca184f037c309e4f7ece0a9ca53bc resources\sound3\deco\mob\squid\death3.ogg

copy assets\objects\eb\eb93722c9adcccba57bc6e9b19039599e94934c8 resources\sound3\deco\mob\squid\hurt1.ogg
copy assets\objects\3c\3c05729b7cfdef0e5782080155004d4d0766ab41 resources\sound3\deco\mob\squid\hurt2.ogg
copy assets\objects\96\96a92abe786f7f1fd5ee56a364c2fbee7f23a165 resources\sound3\deco\mob\squid\hurt3.ogg
copy assets\objects\df\df97decf9d824c4b47c642bbcc3ac6c230e91bc2 resources\sound3\deco\mob\squid\hurt4.ogg

if not exist resources\sound3\deco\misc (
	mkdir resources\sound3\deco\misc
)

if not exist resources\sound3\deco\misc\itemFrame (
	mkdir resources\sound3\deco\misc\itemFrame
)

copy assets\objects\cf\cf44e74d49ef2793fb01143a37fd27ee50d0e727 resources\sound3\deco\misc\itemFrame\addItem1.ogg
copy assets\objects\28\281f889e8eadcb21e2bb664d4f337192fab3a139 resources\sound3\deco\misc\itemFrame\addItem2.ogg
copy assets\objects\e2\e2b2464a1507c129e4fd8aa2447f92cfe1bd61bb resources\sound3\deco\misc\itemFrame\addItem3.ogg
copy assets\objects\28\285196110d802aed2f6797eb436d70e36716edaa resources\sound3\deco\misc\itemFrame\addItem4.ogg

copy assets\objects\36\36adcbb0553274272777667992b363d857b80af9 resources\sound3\deco\misc\itemFrame\break1.ogg
copy assets\objects\c7\c756f26f5a702fca021795d9ae15b5dadba1cf07 resources\sound3\deco\misc\itemFrame\break2.ogg
copy assets\objects\1d\1d15957378b747a2daad165dd14acc5689310e9a resources\sound3\deco\misc\itemFrame\break3.ogg

copy assets\objects\fa\fae49d67f7901e74e75c1343082096fd7f524ee7 resources\sound3\deco\misc\itemFrame\place1.ogg
copy assets\objects\dc\dcddcc8d9845734c85e39afecb34cc2b1586905c resources\sound3\deco\misc\itemFrame\place2.ogg
copy assets\objects\06\06dafda29984d98c81df8cd5edeca2e96599f1f4 resources\sound3\deco\misc\itemFrame\place3.ogg
copy assets\objects\d6\d6a7e9b6882630bc21a0efad67dcccd12ccb2eb5 resources\sound3\deco\misc\itemFrame\place4.ogg

copy assets\objects\83\83c8daabf4016b4a69a0aff792629776e646477a resources\sound3\deco\misc\itemFrame\removeItem1.ogg
copy assets\objects\35\35a5c6a402a0f0091c1221a245ea9cc180975cc8 resources\sound3\deco\misc\itemFrame\removeItem2.ogg
copy assets\objects\54\54ae8bae3da40ea9a80739d7b36106bffaf8854c resources\sound3\deco\misc\itemFrame\removeItem3.ogg
copy assets\objects\7c\7c58d79a33c20c776c6f592b80e96305034c6dbe resources\sound3\deco\misc\itemFrame\removeItem4.ogg

copy assets\objects\c6\c6ac8614f870ba8a7898a2544738d03dc141c92c resources\sound3\deco\misc\itemFrame\rotateItem1.ogg
copy assets\objects\5e\5ebe9a655de4ff8510a2c58e74d25a65ce6c8ad1 resources\sound3\deco\misc\itemFrame\rotateItem2.ogg
copy assets\objects\de\def695c50fa800dc639e91bea0148c7626ca5e87 resources\sound3\deco\misc\itemFrame\rotateItem3.ogg
copy assets\objects\5c\5cd1caeb2b7c35e58c57a90eed97be8cd893e499 resources\sound3\deco\misc\itemFrame\rotateItem4.ogg

if not exist resources\sound3\deco\misc\painting (
	mkdir resources\sound3\deco\misc\painting
)

copy assets\objects\a1\a1aaacc760df41853a7c02e741b81bfc51c5e93c resources\sound3\deco\misc\painting\break1.ogg
copy assets\objects\33\330a29f4ac0f178bb2d5855b29ad1b262ef14604 resources\sound3\deco\misc\painting\break2.ogg
copy assets\objects\7a\7a4f2566e6d1ff536a9c6d7f9a0d804d15a3ce62 resources\sound3\deco\misc\painting\break3.ogg

copy assets\objects\a3\a3ab149628aa2f10acda1e1e3c097b04fcb46328 resources\sound3\deco\misc\painting\place1.ogg
copy assets\objects\22\2211e494688ba04b0c4658ed74d7e48c2d391c33 resources\sound3\deco\misc\painting\place2.ogg
copy assets\objects\f8\f82bb1724f94ef3e2cd80a0e41ed0ca54c8f7502 resources\sound3\deco\misc\painting\place3.ogg
copy assets\objects\08\08d3912d571693d82692458132d4de59928f16d6 resources\sound3\deco\misc\painting\place4.ogg

echo "Done!"
echo "New sounds can be found under resources\sound3\deco"

PAUSE