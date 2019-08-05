package Robinson.Zackery.AndroidCodingChallenge

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class RxUnitTests {

    @Test
    fun just() {
        Observable.just(1, 2, 3).subscribe(object: Observer<Int> {
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
        Observable.fromIterable(listOf(1, 2, 3)).subscribe(object: Observer<Int> {
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
        Observable.just(map).subscribe(object: Observer<Map<Int, String>> {
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
            emitter.onNext(listOf("1", "2", "3")) }).subscribe{ result ->
            System.out.println(result)
        }
    }
}