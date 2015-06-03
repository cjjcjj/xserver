java -Xms50m -Xmx50m -Xmn30m -cp target/newservlet.jar:$(for i in target/lib/*.jar ; do echo -n $i: ; done) com.chuangwai.xserver.NewsServer
