# Hilt and Dependency Injection:
   - design pattern
   - Hilt is based on Dagger2 with thin layer of standartization
   - adventages of Hilt: easier to learn, not as complicated as Dagger, has automaticly generated components
   - the main idea of DI: One class uses a functions(methods) of an another. The second one is a dependency for the first
     (RecyclerViewAdapter depends on Adapter, in MVVM View depends on ViewModel)

  ### Scheme of DI by Dagger2:
  - Module(@Provides) - instantiation logic
  - Component(fun inject(into)) - injection logic(where the instance is injects)!!! Hilt removes a component
  - Target(@Inject) - any file or class. Uses the component to preform an injection

  ### Supported classes:
  - Application(@HiltAndroidApplication)
  - Activity(@ComponentActivity) Hilt supports only children of Component Activity (for exampleAppComponentActivity)
  - Fragment(androidx.Fragment) no retained fragment(which aren't destroyed abd recreated)
  - View
  - Service
  - Broadcast receiver
  - ViewModel not supported by Hilt directly(Jetpack extensions)

## SIMPLE Types of Hilt injections for OWN CLASSES:
  ### Constructor injection(provide Componentto dependency graph and instantiate a certain type)
     - with no parameters class MyClass @Inject constructor()
     -  with parameters class MyClass @Inject constructor(dependency: Dependency)
		       class Dependency	 @Inject constructor()
 ### Field injection(make different types and objects available where we need it)
     - class where we inject must be annotated by @AndroidEntryPoint
     - injected type must be available to Hilt
	@AndroidEntryPoint
	class MainActivity  : AppCompatActivity() {
           @Inject lateinit var databaseAdapter: DatabaseAdapter}
 ### Method injection(inject object directly into a method call) Used rare
    !!! Not needed to call it at all!!! function trigered just when MyClass is became available in the dependency graph!!!
	@Inject
	fun callMyClass(myClass: MyClass){
	   myClass.doSmth()
	}

## DIFFICULT type for Hilt injection
  - Modules (provide a way to instantiate difficult to inject types, allow to define of instantiation of the type for Hilt which and access and provides its instance)
    Interfaces can't be injected by constructor(we should provide the type of the interface for Hilt)
    NOT YOUR OWN types can't be conctructor injected(Glide, Retrofit for example)
   = create a AppModule class
	@Module
	// provide imjections to all activities in the scope
	@InstallIn(ActivityCompat::class)
	class NetworkModule {
	}
  - Interface injection(provide na interface implementation to dependence graph)
	!!! Module class and binding function need to be abstract        
	@Binds
   	abstract fun bindNetworkAdapterImpl(appNetworkAdapterImpl: AppNetworkAdapter): NetworkAdapter
  - Instance injection(clases you don't own should not be an abstract)
	@Provides
   	fun provideNetworkService(): NetworkService =
        	NetworkService.Builder()
            	.host("google.com")
            	.protocol("HTTPS")
            	.build()

## QUALIFIERS(provide different implementation to the same type. For example implementations of an interface)
	@Qualifier
	@Rention(AnnotationRention.BINARY)// how is annotation class is stored
	annotation class MyQualifier

    = create a file Qualifiers and add there annotation classes for each interface implementation class
	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class CallInterceptor {}
    = in module class instantiate a specific class of the interface
    	@CallInterceptor
    	@Provides
    	fun provideCallNetworkService(): NetworkService =
        	NetworkService.Builder()
            	.host("google.com")
            	.protocol("HTTPS")
            	.interceptor(CallInterceptorImpl())
            	.build()
   = in class where you use injected class annotate injection by a qualifier @CallInterceptor
    	@CallInterceptor
    	@Inject lateinit var networkService: NetworkService

## CONTEXT providing(Can be provided in injected class constructor as an injection using a specific qualifier)
	= @ApplicationContext provide an access to not destructable context(such Activity or fragment one)
	= @ActivityContext(access to a current activity)

   Multiple components(for have an access in multilpe places)
	@InstallIn(ActivityComponent::class, ViewComponent::class)
	
## VIEWMODEL INJECTIONS:
   = add dependencies to build gradle file
	// Hilt Jetpack Integrations
   	implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03'
    	kapt 'androidx.hilt:hilt-compiler:1.0.0'
    	implementation 'androidx.fragment:fragment-ktx:1.3.6'

  = annotate viewModel class with Hilt annotations:
	@HiltViewModel
	class DetailViewModel @Inject constructor(application: Application)

  = inject viewModel to fragment or activity as a field
	private val viewModel: DetailViewModel by viewModels()

## UNIT TESTING WITH MOCKITO:
  DI and Hilt gives a possibility to impelemet to a project tests(simple mocking dependencies in tests instead of instantiating)
  = add Mockito framework to dependencies in build gradle
	testImplementation 'org.mockito:mockito-core:3.11.2'
  = create a class FileNameTest with Mockito anntation in the test folder
	@RunWith(MockitoJUnitRunner::class)
	class DatabaseAdapterTest {
	}
  = mock here its constructor arguments !!! Make sure to do mocked classes open
    	@Mock
    	lateinit var mockContext: Context
    
    	@Mock
    	lateinit var mockDatabaseService: DatabaseService

  = create testing function with assert
	@Test
    	fun testDatabaseAdapter(){
        	// instantiate database adapter
        	val adapter = DatabaseAdapter(mockContext, mockDatabaseService)
        	val multRes = adapter.justForTestPurposes(2,2)
        	assert(multRes == 4)
    }

## COMPONENTS
  - Components and lifetimes
    Component binds together module and injectionCreating components is automated by Hilt, and allows to use standart components rather than write our own
	###Supported classes(limited by a scope) and its lifetimes:
	!!! should use scope as small as you can, because of possible conflicts
	  = ApplicationComponent(Access scope of whole application(activities, fragments, serives), created and destroyed as an application)
	    Created: Aplication onCreate() Destroyed: Application onDestroy()
	  = ActivityComponent
	    Created: Activity onCreate() Destroyed: Activity onDestroy()
	  = ActivityRetainedComponent(ViewModel)
	    Created: Activity onCreate() Destroyed: Activity onDestroy()
	  = FragmentComponent
	    Created: Fragment onAttach() Destroyed: Fragment onDestroy()
	  = ViewComponent
	    Created: View super() Destroyed: View destroyed
	  = ViewWithFragmentComponent(@WithFragmentBindings)
	    Created: View super() Destroyed: View destroyed
	  = ServiceComponent
	    Created: Service onCreate() Destroyed: Service onDestroy()
  - Scopes(let us to decide how many objects are created for a binding.)
    !!! By default every binding is unscoped. Scope allows to restrict an injection to certain area
	###List of scopes:
	  = ApplicationComponent - @Singleton(all application scope, only one object will be created at the certain time)
	   !!! scope beneath restrict instances creation, so if for ex. activity was destroyed, there is no its instance exists
	  = ActivityComponent - @ActivityScoped
	  = ActivityRetainedComponent(ViewModel) - @ActivityRetainScoped
	  = FragmentComponent - @FragmentScoped
	  = ViewComponent - @ViewScoped
	  = ViewWithFragmentComponent - @ViewScoped
	  = ServiceComponent - @ServiceScoped
     Add annotation @ActivityScoped to a function you are @Provide
	!!! Make sure it is suitable with a class annotation @InstallIn(ActivityComponent::class)
	
## Hierarchy(children of a module are allowed to access to its parent)
	@Singleton -> @ActivityRetainedScoped, @ServiceScoped -> @ActivityScoped -> @FragmentScoped, @ViewScoped -> @ViewScoped
   
## Implementation: Hilt
  - needs to application class
  - three types of injections:
   = Constructor injection(inject a class without any parameters)
   = Field injection(inject some instances of classes which functions(methods) we need)
   = Method injection(rarely used)

1. Add to build file(project):
   classpath "com.google.dagger:hilt-android-gradle-plugin:2.37"
2. Add to build file(app):
   To plugins add:
    - id 'kotlin-kapt'
    - id 'dagger.hilt.android.plugin'
   To dependencies add:
      - implementation "com.google.dagger:hilt-android:2.37"
      - kapt "com.google.dagger:hilt-android-compiler:2.37"
3. Create class AppNameApplication with Hilt annotation:
    !!! In multimodule app the module of application class must have all dependencies of related modules where target is used
    @HiltAndroidApp
    class ArtTestingApp: Application()
4. Add to manifest file:
    android:name=".AppNameApplication"
5. Add to MainActivity class annotation: @AndroidEntryPoint
   !!!To let Hilt know where the application starts and where will provide dependencies. It generate an individual components
   for each place where dependency is injected. I must annotate by it all classes depend on(use) this class
