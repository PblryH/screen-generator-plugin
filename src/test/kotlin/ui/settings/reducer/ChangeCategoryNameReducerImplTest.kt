package ui.settings.reducer

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.test.TestCoroutineScope
import model.Category
import model.CategoryScreenElements
import model.ScreenElement
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import ui.settings.SettingsState

class ChangeCategoryNameReducerImplTest : BaseReducerTest() {

    @Mock
    private lateinit var updateCategoryReducerMock: UpdateCategoryReducer

    private lateinit var reducer: ChangeCategoryNameReducerImpl

    @Before
    fun setup() {
        reducer = ChangeCategoryNameReducerImpl(state, effectMock, TestCoroutineScope(), updateCategoryReducerMock)
    }

    @Test
    fun `if selected element not null on invoke`() {
        state.value = SettingsState(
            categories = listOf(
                CategoryScreenElements(
                    Category(),
                    listOf(ScreenElement())
                )
            ),
            selectedCategoryIndex = 0
        )

        reducer.invoke("test")

        verify(updateCategoryReducerMock).invoke(Category(name = "test"))
    }

    @Test
    fun `if selected element null on invoke`() {
        reducer.invoke("test")

        verifyZeroInteractions(updateCategoryReducerMock)
    }
}