@GrabResolver(name="msgpack-repo", root="http://msgpack.org/maven2/")
@Grab("org.msgpack:msgpack:0.6.12")

import java.util.ArrayList
import java.util.List
import org.msgpack.MessagePack
import org.msgpack.Templates.*

long epoch = System.currentTimeMillis()/1000;

/**
- シリアライズするオブジェクトを作成
 - new ArrayList<String>() で配列を作成
 - src.add で配列に要素を追加
**/
def src = new ArrayList<String>()
src.add("rundeck.test")
src.add(epoch)
src.add('{"foo":"bar"}')

/**
- シリアライズ
 - MessagePack.pack(src) でシリアライズ
 - println "raw=$raw" でシリアライズした中身を出力
**/
byte[] raw = MessagePack.pack(src)
println "raw=${raw}"

/**
- デシリアライズ
 - MessagePack.unpack(raw)
**/
def dst = MessagePack.unpack(raw)
//println "dst=${dst.dump()}"
println "dst=${dst}"

socket = new Socket("127.0.0.1", 24224);
socketStream = socket.getOutputStream();
socketStream << raw

socket.close();
