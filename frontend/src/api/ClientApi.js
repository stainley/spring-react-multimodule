export const fetchAllClient = async () => {
    const response = await fetch('/clients');
    return await response.json();
};

export const removeByIdClient = async (id) => {
    return await fetch(`/clients/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
};

export const loadClientById = async (props) => {
    return await (
        await fetch(`/clients/${props.match.params.id}`)
    ).json();
};

export const updateOrCreateClient = async (item) => {
    return await fetch('/clients' + (item.id ? '/' + item.id : ''), {
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
};




