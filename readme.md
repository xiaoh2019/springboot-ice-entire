服务端没有集成框架，代码部分如下
~~~~
``
public class IceMain {
         public static void main(String[] args) {
             // 通信器
             Communicator ic = null;
             try {
                 // 初始化这个通信器
                 ic = Util.initialize(args);
                 //创建ice适配器,将服务调用地址和服务映射起来 "HelloServiceAdapter"是适配器名, "default -p 1888"是服务调用的地址
                 ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("ObjectAdapter","default -p 8899");
     
                 // 将服务的具体实现类servant交给这个适配器
                 com.zeroc.Ice.Object servant = new HelloImpl();
                 // "HelloIce"--服务接口在ice中注册名,转成唯一标识identity
                 Identity id = Util.stringToIdentity("HelloIce");
                 adapter.add(servant, id);
     
                 //往adapter添加新的服务
                 PersonServiceImpl personService = new PersonServiceImpl();
                 //每个服务通过Identity来区分，远程调用需要指定PersonService服务
                 Identity personServiceId = Util.stringToIdentity("PersonService");
                 adapter.add(personService, personServiceId);
                 // 激活这个适配器
                 adapter.activate();
                 System.out.println("end");
                 ic.waitForShutdown();
             }catch (Exception e){
                 e.printStackTrace();
             }finally {
                 if (ic != null){
                     ic.destroy();
                 }
             }
             System.exit(1);
         }
     }
``



public class PersonServiceImpl implements PersonService {
    private List<Person> personMap = new ArrayList<>();
    @Override
    public Person getPersonByName(String name, Current current) {
        System.out.println("收到了查询请求");
        printCurrentInfo(current);
        for (Person person : personMap) {
            if (name.equals(person.name)){
                return person;
            }
        }
        return null;
    }

    @Override
    public void addPerson(Person person, Current current) {
        System.out.println("收到了添加请求");
        printCurrentInfo(current);
        personMap.add(person);
    }

    @Override
    public void update(Person p, Current current) {
        System.out.println("收到了更新请求");
        for (int i = 0; i < personMap.size(); i++) {
            Person person = personMap.get(i);
            if (p.id == person.id){
                person.name = p.name;
                person.age = p.age;
                person.email = p.email;
                person.address = p.address;
            }
        }
    }

    @Override
    public void deletePersonById(long id, Current current) {
        System.out.println("收到了删除请求");
        printCurrentInfo(current);
        for (int i = 0; i < personMap.size(); i++) {
            Person person = personMap.get(i);
            if (id == person.id){
                personMap.remove(i);
            }
        }
    }

    private void printCurrentInfo(Current current){
        //调用的方法
        System.out.println("operation" + current.operation);
        //id  3或者其他数字
        System.out.println("requestId" + current.requestId);
        //PersonService
        System.out.println("name" + current.id.name);
        System.out.println("facet" + current.facet);
    }
}