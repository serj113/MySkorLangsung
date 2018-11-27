package com.setia.myfootballmatch

import com.setia.myfootballmatch.detail.EventDetailPresenter
import com.setia.myfootballmatch.detail.EventDetailView
import com.setia.myfootballmatch.model.Event
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventDetailPresenterTest {
    @Mock
    private
    lateinit var view: EventDetailView

    private lateinit var presenter: EventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view)
    }

    @Test
    fun testShowEventDetail()  {
        val event = Event()
        val teamId = "576590"

        GlobalScope.launch {
            presenter.getEventDetail(teamId)

            Mockito.verify(view).updateData(event)
            Mockito.verify(view).updateAwayLogo(event.idAwayTeam ?: "")
            Mockito.verify(view).updateHomeLogo(event.idHomeTeam ?: "")
        }
    }

}