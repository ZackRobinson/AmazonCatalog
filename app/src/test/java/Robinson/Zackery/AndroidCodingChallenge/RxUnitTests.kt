package Robinson.Zackery.AndroidCodingChallenge

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import java.util.concurrent.TimeUnit

// Interview prep
//  https://www.veskoiliev.com/40-rxjava-interview-questions-and-answers/
//  https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
// Decision Tree of Observable Operators
//  http://reactivex.io/documentation/operators.html
class RxUnitTests {

    // https://medium.com/@gabrieldemattosleon/fundamentals-of-rxjava-with-kotlin-for-absolute-beginners-3d811350b701
    @Test
    fun just_Strings() {
        Observable.just("Apple", "Orange", "Banana")
            .subscribe(
                { value -> println("Received: $value") }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }                 // onComplete
            )
    }

    @Test
    fun just_Array() {
        Observable.fromArray("Apple", "Orange", "Banana")
            .subscribe { println(it) }
    }

    @Test
    fun just_Iterable() {
        Observable.fromIterable(listOf("Apple", "Orange", "Banana"))
            .subscribe(
                { value -> println("Received: $value") },      // onNext
                { error -> println("Error: $error") },         // onError
                { println("Completed") }                       // onComplete
            )
    }

    @Test
    fun create_Observable() {
        fun getObservableFromList(myList: List<String>) =
            Observable.create<String> { emitter ->
                myList.forEach { kind ->
                    if (kind == "") {
                        emitter.onError(Exception("There's no value to show"))
                    }
                    emitter.onNext(kind)
                }
                emitter.onComplete()
            }

        getObservableFromList(listOf("Apple", "Orange", "Banana")).also { println(it.javaClass) }
            .forEach { println(it) }.also { println(it.javaClass) }
        getObservableFromList(listOf("Apple", "Orange", "Banana")).subscribe { println("Received: $it") }
    }

    @Test
    fun interval() { // TODO Fix
        Observable.intervalRange(
            10L,     // Start
            5L,      // Count
            0L,      // Initial Delay
            1L,      // Period
            TimeUnit.SECONDS
        ).subscribe { println("Result we just received: $it") }
    }

    @Test
    fun just() {
        Observable.just(1, 2, 3).subscribe(object : Observer<Int> {
            override fun onNext(t: Int) {
                System.out.println(t)
            }

            override fun onError(e: Throwable) {
                System.out.println("onError()")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("onSubscribe()")
            }

            override fun onComplete() {
                System.out.println("onComplete()")
            }
        })
    }

    @Test
    fun from() { // Like just(), but must pass an iterable, list, etc...
        Observable.fromIterable(listOf(1, 2, 3)).subscribe(object : Observer<Int> {
            override fun onNext(t: Int) {
                System.out.println(t)
            }

            override fun onError(e: Throwable) {
                System.out.println("onError()")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("onSubscribe()")
            }

            override fun onComplete() {
                System.out.println("onComplete()")
            }
        })
    }

    @Test
    fun justWithMaps() {
        val map = mapOf(1 to "x", 2 to "y", 3 to "z")
        Observable.just(map).subscribe(object : Observer<Map<Int, String>> {
            override fun onNext(t: Map<Int, String>) {
                System.out.println(t.toString())
            }

            override fun onError(e: Throwable) {
                System.out.println("onError()")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("onSubscribe()")
            }

            override fun onComplete() {
                System.out.println("onComplete()")
            }
        })
    }

    @Test
    fun observable() {
        val obs = Observable.just("Hello")
            .doOnSubscribe { println("Subscribed") }
            .doOnNext { s -> println("Received: $s") }
            .doAfterNext { println("After Receiving") }
            .doOnError { e -> println("Error: $e") }
            .doOnComplete { println("Complete") }
            .doFinally { println("Do Finally!") }
            .doOnDispose { println("Do on Dispose!") }
            .subscribe { println("Subscribe") }
        println(obs.javaClass)
        obs.dispose()
    }

    @Test
    fun observableNoBackpressure() {
        val observable = PublishSubject.create<Int>()
        observable
            .observeOn(Schedulers.computation())
            .subscribe({ println("The Number Is: $it") }, { t -> print(t.message) })

        for (i in 0..1000000) {
            observable.onNext(i)
        }
    }

    @Test
    fun observableToFlowable() {
        val observable = PublishSubject.create<Int>()
        observable
            .toFlowable(BackpressureStrategy.BUFFER) // Check out DROP and LATEST and BUFFER
            .onBackpressureBuffer(1000000)
            .observeOn(Schedulers.computation())
            .subscribe({ println("The Number Is: $it") }, { t -> print(t.message) })

        for (i in 0..1000000) {
            observable.onNext(i)
        }
    }

    @Test
    fun create() {
        Observable.create(ObservableOnSubscribe<List<String>> { emitter ->
            emitter.onNext(listOf("1", "2", "3"))
        }).subscribe { result ->
            System.out.println(result)
        }
    }

    // If we use this class and there is a value emitted, onSuccess will be called. If there’s no value, onError will be called.
    @Test
    fun single() {
        Single.just("This is a Single")
            .subscribe(
                { v -> println("Value is: $v") },
                { e -> println("Error: $e") }
            )
    }

    // The methods are mutually exclusive, in other words, only one of them is called.
    // If there is an emitted value, it calls onSuccess , if there’s no value, it calls onComplete or if there’s an error, it calls onError .
    @Test
    fun maybe() {
        Maybe.just("This is a Maybe")
            .subscribe(
                { value -> println("Received: $value") },
                { error -> println("Error: $error") },
                { println("Completed") }
            )
    }

    @Test
    fun completable() {
        Completable.create { emitter ->
            emitter.onComplete()
            emitter.onError(Exception())
        }
    }

}