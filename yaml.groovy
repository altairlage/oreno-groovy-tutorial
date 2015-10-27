@Grapes([
  @Grab(group='org.jyaml', module='jyaml', version='1.3'),
])

import org.ho.yaml.Yaml;

def input = ''' 
- 1 
- apple 
- orange 
''' 
def foo = Yaml.load(input)
def topic_arn = Yaml.load(new File("setting.yml"));

println foo
println topic_arn.get("topic_arn")
