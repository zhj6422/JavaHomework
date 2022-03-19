# netty-gateway

- HttpInboundServer作为程序的入口，配置ServerBootstrap，将HttpInboundInitializer添加位childrenHandler

- HttpInboundInitializer中，往pipeline中加入Handler

- HttpInboundHandler中，重写channelRead方法，当收到客户端发来的请求时，执行这个方法，将请求交给另一个handler进行处理（HttpOutboundHandler）

- HttpOutboundHandler中，通过一个线程池对请求进行处理，每次从备选的几个url中由router随机选择一个url作为请求url，交给线程池，执行fetchGet方法

  fetchGet方法中，请求url

  对请求返回的数据，封装到一个FullHttpResponse返回，并对结果进行过滤