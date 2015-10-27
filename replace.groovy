//def text = 'Rundeck JOB: ${job.status} [${job.project}] \"${job.name}\" run by ${job.user} (#${job.execid}) ${job.group}'
def text = 'ap-northeast-1'
def tokens=[
    '${job.status}': "foo".toUpperCase(),
    '${job.project}': "bar",
    '${job.name}': "hoge",
    '${job.group}': "baz",
    '${job.user}': "aho",
    '${job.execid}': "12345"
]

//println tokens
//def result = text.replaceAll(/(\$\{\S+?\})/){
    //println it[0]
    //tokens[it[0]]
    //tokens
    //System.err.println("DEBUG: token0="+tokens)
    //if(tokens[it[1]]){
    //    tokens[it[1]]
    //} else {
    //    it[0]
    //}
//}

//def result = text.toUpperCase()
def result = text.replaceAll(/\U/)

println result
