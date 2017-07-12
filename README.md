This project is based on `sia_p134_5_spring_web_basic_setup`, but `spittr.config.SpittrWebAppInitializer` is changed.

This project shows how to use `WebApplicationInitializer` instead of `AbstractAnnotationConfigDispatcherServletInitializer` to setup Spring MVC, but it only works on servlet 3.0 supported container, as well.

> https://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
>
> https://github.com/bkielczewski/example-spring-mvc-initializer