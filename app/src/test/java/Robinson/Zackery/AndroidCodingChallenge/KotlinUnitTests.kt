package Robinson.Zackery.AndroidCodingChallenge;

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

// https://kotlinlang.org/docs/reference/coding-conventions.html
class KotlinUnitTests {

    // Modifiers
    //  If a declaration has multiple modifiers, always put them in the following order:
    //    public / protected / private / internal
    //    expect / actual
    //    final / open / abstract / sealed / const
    //    external
    //    override
    //    lateinit
    //    tailrec
    //    vararg
    //    suspend
    //    inner
    //    enum / annotation
    //    companion
    //    inline
    //    infix
    //    operator
    //    data


    // Example from Wharton's paper on Project Kotlin for Android
    // https://docs.google.com/document/d/1ReS3ep-hjxWA8kZi0YqDbEhCqTt29hG8P44aA9W0DM8/edit
    @Test
    fun elvis_operator() {
        val x: String? = "Hi"
        val y: String? = null
        val lengX = x?.length ?: -1
        val lengY = y?.length ?: -1
        assert(lengX == 2)
        assert(lengY == -1)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // Great explanation on extension functions
    // https://dzone.com/articles/examining-kotlins-also-apply-let-run-and-with-intentions
    @Test
    fun list_onEach() {
        listOf(1, 2, 3, 4, 5).onEach { System.out.println(it) } // executes without assignment
        val list = listOf(1, 2, 3, 4, 5).onEach { System.out.println(it) }
        assert(list == listOf(1, 2, 3, 4, 5))
    }

    @Test
    fun list_map() {
        val list = listOf(1, 2, 3, 4, 5).map { it * 5 }
        assert(list == listOf(5, 10, 15, 20, 25))
    }

    @Test
    fun list_flapMap() {
        val nums = listOf(1, 2)
        val strings = listOf("a", "b", "c", "d")
        val numsStrings = nums.flatMap { strings }
        System.out.println(numsStrings)
    }

    // flatMap merges the two collections into a single one.
    // Map simply results in a list of lists.
    @Test
    fun list_flapMap_vs_map() {
        val letters = listOf("a","b","c")
        val nums = listOf("1","2","3")
        val data = listOf(letters, nums)

        val combinedFlatMap = data.flatMap { it }
        println(combinedFlatMap)

        val combinedMap = data.map { it }
        println(combinedMap)
    }

    @Test
    fun list_let() {
        val i = listOf(1, 2, 3, 4, 5).let { it[4] * 5 }
        System.out.println(i)
        assertTrue(i == 25)
    }

    @Test
    fun list_letListBeMutable() {
        val i = listOf(1, 2, 3, 4, 5).let { it.toMutableList() }
        i[0] = 1
        val k = listOf(1, 2, 3, 4, 5).let { it }
        // k[0] = 1    // Doesn't Compile, k isn't mutable
        System.out.println(i)
    }

    @Test
    fun list_map_filter() {
        val list = listOf(1, 2, 3, 4, 5).map { it * 5 }.filter { it > 10 }
        assert(list == listOf(15, 20, 25))
    }

    @Test
    fun list_map_filter_onEach() {
        val list = listOf(1, 2, 3, 4, 5).map { it * 5 }.onEach { System.out.println(it)}.filter { it > 10 }.onEach { System.out.println(it)}
        // Output: 5 10 15 20 25 15 20 25
        assert(list == listOf(15, 20, 25))
    }

    @Test
    fun list_map_filter_also() {
        val list = listOf(1, 2, 3, 4, 5).map { it * 5 }.also { System.out.println(it)}.filter { it > 10 }.also { System.out.println(it)}
        // Output:
        // [5, 10, 15, 20, 25]
        // [15, 20, 25]
        assert(list == listOf(15, 20, 25))
    }

    // Common Design Patterns
    //  https://www.raywenderlich.com/470-common-design-patterns-for-android-with-kotlin

    @Test
    fun coroutine() { // Use Thread.sleep(2000) to unit test a network request?
        println("Start")

        // Start a coroutine
        GlobalScope.launch {
            delay(1000)
            println("Hello")
        }

//        Thread.sleep(2000) // wait for 2 seconds
        println("Stop")
    }


}
