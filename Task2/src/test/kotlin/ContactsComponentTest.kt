import org.junit.Test
import kotlin.test.assertEquals

class ContactsComponentTest {
    private fun createContact(data: String, unix: String): Contact {
        return Contact().also { contact ->
            contact.data = data
            contact.id = "1"
            contact.last_used = Time().also { time ->
                time.unix = unix
            }
        }
    }

    private val contactAOld = createContact("A", "123456")
    private val contactANew = createContact("A", "12345679")
    private val contactBNew = createContact("B", "12345681")
    private val contactBOld = createContact("B", "12345")
    private val contactCOld = createContact("C", "12345")
    private val contactCNew = createContact("C", "12345680")

    @Test
    fun `when more contacts then return only 3 sorted and distinct`() {
        val contactsComponent = ContactsComponent()

        contactsComponent.contacts = listOf(
            contactBOld,
            contactBOld,
            contactAOld,
            contactCNew,
            contactBOld,
            contactANew,
            contactCNew,
            contactAOld,
            contactBNew,
            contactBOld,
            contactCOld,
            contactBOld,
            contactCNew
        )

        val result = contactsComponent.getRecentContacts()

        assertEquals(listOf(contactBNew, contactCNew, contactANew), result)
    }

    @Test
    fun `when empty list of contacts then return empty list`() {
        val contactsComponent = ContactsComponent()

        contactsComponent.contacts = listOf()

        val result = contactsComponent.getRecentContacts()

        assertEquals(emptyList(), result)
    }

    @Test
    fun `when only one data type of contacts then return single element list`() {
        val contactsComponent = ContactsComponent()

        contactsComponent.contacts = listOf(
            contactAOld, contactAOld, contactANew, contactAOld
        )

        val result = contactsComponent.getRecentContacts()

        assertEquals(listOf(contactANew), result)
    }

    @Test
    fun `when only two data types of contacts then return two element list`() {
        val contactsComponent = ContactsComponent()

        contactsComponent.contacts = listOf(
            contactCNew, contactAOld, contactANew, contactAOld
        )

        val result = contactsComponent.getRecentContacts()

        assertEquals(listOf(contactCNew, contactANew), result)
    }

    @Test
    fun `when contacts null then return empty list`() {
        val contactsComponent = ContactsComponent()

        val result = contactsComponent.getRecentContacts()

        assertEquals(emptyList(), result)
    }
}