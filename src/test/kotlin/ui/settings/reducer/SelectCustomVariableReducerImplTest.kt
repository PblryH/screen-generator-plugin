package ui.settings.reducer

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import model.Category
import model.CategoryScreenElements
import model.CustomVariable
import org.junit.Before
import org.junit.Test
import ui.settings.SettingsState
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SelectCustomVariableReducerImplTest : BaseReducerTest() {

    private lateinit var reducer: SelectCustomVariableReducerImpl

    @Before
    fun setup() {
        reducer = SelectCustomVariableReducerImpl(state, effectMock, TestCoroutineScope())
    }

    @Test
    fun `when custom variables empty on invoke`() {
        reducer.invoke(0)

        assertEquals(
            SettingsState(
                selectedCustomVariableIndex = null,
            ),
            state.value
        )
    }

    @Test
    fun `when index not in bounds on invoke`() {
        val initialState = SettingsState(
            categories = listOf(CategoryScreenElements(Category(), emptyList())),
            selectedCategoryIndex = 0
        )
        state.value = initialState

        reducer.invoke(10)

        assertEquals(
            initialState.copy(
                selectedCustomVariableIndex = null,
            ),
            state.value
        )
    }

    @Test
    fun `when index in bounds on invoke`() {
        val initialState = SettingsState(
            categories = listOf(
                CategoryScreenElements(
                    Category(customVariables = listOf(CustomVariable("test"))),
                    emptyList()
                )
            ),
            selectedCategoryIndex = 0
        )
        state.value = initialState

        reducer.invoke(0)

        assertEquals(
            initialState.copy(
                selectedCustomVariableIndex = 0
            ),
            state.value
        )
    }
}