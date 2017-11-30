package com.bwie.sss.bean

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/30
 */
class FindDetail {

data class Detail(
		val itemList: List<Item>,
		val count: Int, //10
		val total: Int, //0
		val nextPageUrl: String //http://baobab.kaiyanapp.com/api/v3/videos?start=10&num=10&categoryName=%E7%94%9F%E6%B4%BB&strategy=date
)

data class Item(
		val type: String, //video
		val data: Data,
		val tag: Any, //null
		val id: Int //0
)

data class Data(
		val dataType: String, //VideoBeanForClient
		val id: Int, //63463
		val title: String, //看完这部视频，以后不吃鸭蛋了！
		val slogan: Any, //null
		val description: String, //看完这部视频，以后不吃鸭蛋了！
		val provider: Provider,
		val category: String, //生活
		val author: Author,
		val cover: Cover,
		val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=63463&editionType=default&source=aliyun
		val thumbPlayUrl: Any, //null
		val duration: Int, //297
		val webUrl: WebUrl,
		val releaseTime: Long, //1511970727000
		val library: String, //DEFAULT
		val playInfo: List<Any>,
		val consumption: Consumption,
		val campaign: Any, //null
		val waterMarks: Any, //null
		val adTrack: Any, //null
		val tags: List<Tag>,
		val type: String, //NORMAL
		val titlePgc: String, //看完这部视频，以后不吃鸭蛋了！
		val descriptionPgc: String, //看完这部视频，以后不吃鸭蛋了！
		val remark: String,
		val idx: Int, //0
		val shareAdTrack: Any, //null
		val favoriteAdTrack: Any, //null
		val webAdTrack: Any, //null
		val date: Long, //1511970727000
		val promotion: Any, //null
		val label: Any, //null
		val labelList: List<Any>,
		val descriptionEditor: String,
		val collected: Boolean, //false
		val played: Boolean, //false
		val subtitles: List<Any>,
		val lastViewTime: Any, //null
		val playlists: Any //null
)

data class Tag(
		val id: Int, //520
		val name: String, //大自然
		val actionUrl: String, //eyepetizer://tag/520/?title=%E5%A4%A7%E8%87%AA%E7%84%B6
		val adTrack: Any, //null
		val desc: Any, //null
		val bgPicture: String, //http://img.kaiyanapp.com/4f8c478d7753f65e4ec3407b3d055edf.jpeg?imageMogr2/quality/100
		val headerImage: String, //http://img.kaiyanapp.com/036745f32252000f3b91efc21aafcaf1.jpeg?imageMogr2/quality/100
		val tagRecType: String //NORMAL
)

data class Provider(
		val name: String, //PGC
		val alias: String, //PGC
		val icon: String
)

data class Author(
		val id: Int, //2080
		val icon: String, //http://img.kaiyanapp.com/97679c791d6332d9e9d50bec0993f4b4.png?imageMogr2/quality/60/format/jpg
		val name: String, //丛林之家
		val description: String, //带你体验向往的生活，看不一样的世界！
		val link: String,
		val latestReleaseTime: Long, //1511970727000
		val videoNum: Int, //13
		val adTrack: Any, //null
		val follow: Follow,
		val shield: Shield,
		val approvedNotReadyVideoCount: Int, //0
		val ifPgc: Boolean //true
)

data class Follow(
		val itemType: String, //author
		val itemId: Int, //2080
		val followed: Boolean //false
)

data class Shield(
		val itemType: String, //author
		val itemId: Int, //2080
		val shielded: Boolean //false
)

data class Cover(
		val feed: String, //http://img.kaiyanapp.com/881b889259d4a8c942dec95be65d05ee.png?imageMogr2/quality/60/format/jpg
		val detail: String, //http://img.kaiyanapp.com/881b889259d4a8c942dec95be65d05ee.png?imageMogr2/quality/60/format/jpg
		val blurred: String, //http://img.kaiyanapp.com/5436130019637e88ff5516c9bfadbaf3.jpeg?imageMogr2/quality/60/format/jpg
		val sharing: Any, //null
		val homepage: Any //null
)

data class Consumption(
		val collectionCount: Int, //8
		val shareCount: Int, //9
		val replyCount: Int //2
)

data class WebUrl(
		val raw: String, //http://www.eyepetizer.net/detail.html?vid=63463
		val forWeibo: String //http://www.eyepetizer.net/detail.html?vid=63463
)
}